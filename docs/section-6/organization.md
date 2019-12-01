We use what's called the Gitflow workflow, which you can read about in detail [over here](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow).

Essentially, it boils down to these main points:

- The `master` branch should only have stable, tested code.
- Feature branches branch off `dev` and merge back into `dev` when completed. Feature branches should be the only places where new features are added. 
- When we reach a point, a release branch is branched off dev, tested and fixed if necessary, and merged into both `master` and back into `dev` when we deem it stable enough.
- Hotfix branches branch off `master` when we need to fix a problem that is in the `master` branch, and are merged back when the problem is fixed.