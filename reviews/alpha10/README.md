# alpha.10: invalid pending challenges

A pending request previously kept the rival busy for up to 30 seconds even
after the challenger disappeared or changed owner. The shared challenge
lookup now discards unavailable/dead participants and mismatched ownership
before checking whether the rival is busy. Valid requests still require the
rival owner's acceptance and keep their existing expiry.

Startup logging now reads the mod version from metadata instead of reporting
the hardcoded alpha.4. The source remains in the v19 ZIP, following the
repository's existing packaging. `source.patch` compares it with alpha.9;
`visual-assets-sha256.json` records the 33 unchanged visual assets.

## Validation

- Both new Minecraft GameTests fail before the change; 13 existing cases pass.
- After the change, all 15 required GameTests pass. Both replacement requests
  can be accepted; the existing owner-consent/cancellation cases still pass.
- Normal Java21/Gradle9.2.1 build and four static validators pass, including
  all 12 visual/noncombat checks. Only the log statement is normalized for
  the historical registration hash comparison; registrations remain pinned.
- Runtime log identifies alpha.10. Source and compiled JAR preserve all
  33 visual assets; the mod JAR excludes Fowl API and development QA classes.
- Local server tests skip client asset downloads using an existing cache
  reference. CI retains full server/client validation and verifies the log.

Full-modpack multiplayer testing remains manual. No installed instance was
changed. OpenCode remains blocked per the existing provider-status record;
no independent advisor review is claimed. CI results are recorded in the PR.
