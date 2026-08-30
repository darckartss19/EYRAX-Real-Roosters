#!/usr/bin/env bash
set -euo pipefail

asset_root="src/main/resources/assets/eyrax_real_roosters/textures"
mkdir -p "$asset_root/item" "$asset_root/entity/rooster" "$asset_root/entity/hen" "$asset_root/entity/chick"

breeds=(criollo_dominicano rhode_island_red leghorn plymouth_rock brahma silkie ayam_cemani sussex)
colors=(8b4a2b a52a1f f1ead4 7b7065 b99a73 d9d1c4 171719 e8dfc7)
patterns=(solid pinto manilo giro cenizo jabao)

for i in "${!breeds[@]}"; do
  breed="${breeds[$i]}"
  base="#${colors[$i]}"
  for pattern in "${patterns[@]}"; do
    case "$pattern" in
      solid) accent="$base" ;;
      pinto) accent="#eee4cf" ;;
      manilo) accent="#d5a22d" ;;
      giro) accent="#9d1f1f" ;;
      cenizo) accent="#777b82" ;;
      jabao) accent="#292929" ;;
    esac
    for sex in rooster hen chick; do
      out="$asset_root/entity/$sex/${breed}_${pattern}.png"
      convert -size 64x64 "xc:$base" \
        -fill "$accent" -draw 'rectangle 0,0 15,15 rectangle 24,0 39,15 rectangle 0,18 31,37 rectangle 34,18 53,37 rectangle 24,38 51,47' \
        -fill '#d4a832' -draw 'rectangle 18,0 27,7 rectangle 0,38 7,47' \
        -fill '#9f1e1e' -draw 'rectangle 28,0 39,9 rectangle 40,0 47,7' \
        -fill '#241a13' -draw 'rectangle 10,0 11,1 rectangle 20,0 21,1' \
        -colors 32 "$out"
    done
  done
done

convert -size 16x16 xc:none -fill '#8a5a2b' -draw 'polygon 2,14 6,3 9,3 5,14' \
  -fill '#e8c44b' -draw 'circle 10,5 10,1 circle 12,8 12,5' -colors 16 "$asset_root/item/breed_analyzer.png"
convert -size 16x16 xc:none -fill '#b9c1c7' -draw 'polygon 2,12 10,4 14,5 6,13' \
  -fill '#6f777d' -draw 'rectangle 3,12 6,15' -colors 16 "$asset_root/item/training_whistle.png"

convert -size 256x256 radial-gradient:'#f2bb35-#5b160f' \
  -fill '#fff1c2' -stroke '#2d0a06' -strokewidth 6 \
  -draw 'circle 128,128 128,48' \
  -fill '#8c1d17' -stroke none -draw 'polygon 83,115 97,72 112,96 128,64 143,96 160,72 174,115' \
  -fill '#f4d34f' -draw 'polygon 87,126 169,126 151,178 105,178' \
  -fill '#151515' -draw 'circle 110,132 110,123 circle 146,132 146,123' \
  "$asset_root/../../../eyrax_real_roosters.png"
