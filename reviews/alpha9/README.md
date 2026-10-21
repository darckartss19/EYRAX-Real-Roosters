# alpha.9 corrections

When a player cancelled a pending whistle challenge after moving to another
dimension, the rival retained the request and its owner could still accept.
Cancellation now resolves the saved dimension and clears only a request
belonging to that initiator, without loading chunks.

The alpha.9 v18 ZIP follows this repository's existing source-archive layout.
The root Java project remains historical 0.2.1. `source.patch` is the readable
comparison against the alpha.8 v17 ZIP; the added binary wrapper is listed
by checksum. `visual-assets-sha256.json` records the 33 preserved visual files.

The source archive also restores the missing official Gradle 9.2.1 wrapper
JAR and updates documentation, version labels and QA dependency ranges.
The full build workflow now selects the alpha.9 archive and expects 13 tests.

## Local verification — Windows / Java 21 / Gradle 9.2.1

- Before fix: the new cross-dimension GameTest failed; the other 12 passed.
- After fix: all 13 required combat GameTests passed; Gradle exited 0.
- Normal build passed. JAR metadata identifies alpha.9; Fowl API remains
  separate, and no QA classes or Blockbench projects entered the mod JAR.
- Gecko assets, render budget, release structure and 12 visual regression
  checks passed. All 33 geometry/animation/texture files are byte-identical
  to alpha.8 in both source and compiled JAR.
- Local GameTests skipped client asset downloads using a local cache
  reference. CI retains its normal download and visual/server checks.

No interactive client or full-modpack test has been performed for alpha.9.
The dimension regression simulates the player's transferred persistent data
with a same-UUID fake player in the Nether, rather than driving two clients
through a portal. OpenCode inference was rejected by its free-tier service;
no OpenCode review is claimed. No production JAR has been replaced.
