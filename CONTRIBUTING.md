# Contributing to SOLID Inject

Although the project is still in alpha it is open to contributions.
In fact, this guide is open to contributions.

The following are required before a pull request will be merged:

- All code must be correctly formatted.
  See the code convention spec below

## Code formatting conventions

IntelliJ IDEA codestyles

## Source control conventions

### Commit messages

- The summary must be only a single line
- Capitalize the summary
- Do not end the summary with a period
- Use the imperative mood in the subject line (i.e `Add` vs `Added`/`Adding`)
- Separate the summary line from the body with a blank line
- Limit all content to 72 characters in width
- Use the body to explain what and why vs. how
- It is good to include issue references and other tags, but these are not of prime importance and so should never be at the front of the summary.

### Branch structure

- Never merge `master` into a development branch.
  If you need changes from master or to resolve conflicts, please rebase.
- All other branches should be considered ephemeral and prone to change at any time.
  Naturally, if developers are collaborating on a branch they should establish a way to do so effectively.
- Branches should preferably, but not necessarily, be rebased onto the latest `master` commit.
- Merges to `master` to force a merge commit. i.e. `git merge --no-ff`
