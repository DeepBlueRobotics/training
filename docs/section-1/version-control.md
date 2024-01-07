So you know how to write Java code, and you know the basics of how our robot code is written. Now, you might be wondering, how do we collaborate and write code as a team? Surely, we don't take turns passing around a computer to type in our lines. Enter version control. 

[Here's a handy article about it](https://www.freecodecamp.org/news/learn-the-basics-of-git-in-under-10-minutes-da548267cc91/). Stop at the steps, you don't need to do them.

Once you're done, we'll do some exercises to see if you've got it.

!!! note
    We'll be using [VS Code's built-in version control tools in this tutorial](https://code.visualstudio.com/docs/editor/versioncontrol), but there are many other good tools too, including [Github Desktop](https://desktop.github.com/), [Gitkraken](https://www.gitkraken.com/git-client), and of course, the ol' command line. You don't need to know how to use the command line for FRC, but it's a useful, if not essential, skill if you want to go beyond.

Navigate to this [google doc](https://docs.google.com/document/d/1D_4Fbr4ktwU9jl2Mkpw2Kpv0Q4v-NhCO2m5agvL5TfY/edit) and add your gitHub username to the list.

## Practice
VS Code is the text editor we will be using to write and run our code, and should have been installed with the WPILib suite during setup. Open up VS Code and follow the "Cloning a repository" section of [the VS Code git tutorial](https://code.visualstudio.com/docs/editor/versioncontrol#_cloning-a-repository) to clone [this repo](https://github.com/DeepBlueRobotics/training/).

Have it? I hope so too.

Make a new branch, whether by VS Code or Github Desktop or anything else, and title it your own name. We really don't want you doing the next bits on our official training website.

Go find every single file with the name `intro.md`, and paste your name somewhere over it. Or all over it. Horribly deface that file with your name. 
Think of this as a "ritual" of public defacement in order to initiate your learning of Github.

Now stage and commit your changes with the message `add [your name]`, and then push your changes to make the branch public. Refer to the [Git tutorial](https://guides.github.com/introduction/git-handbook/) and [VS Code tutorial](https://code.visualstudio.com/docs/editor/versioncontrol) if needed.

Great! You now have a crimes done to the training website under your name!
But in order to let us know you did it, go to github and create a "Pull Request" from your branch to the `master` branch. It should look a bit like this:
`base: master  <-  compare: willsbranch`

!!! question
    Why and what is a Pull Request? 
A PR (our shorthand for Pull Request) is used to ask for approval to copy the changes of the `compare` branch onto the `base` branch. This is very useful when we want to review people's code before they update the master branch and break the robot, or when a person spends multiple worksessions working on a specific thing and don't want to leave unfinished code on the master branch.

Finish that PR, and we'll delete your branch to clean it up in a few years.

!!! note 
    If you look at the tutorial above, they mention the "Git: Clone" command from the Command Palette (⇧⌘P). However, it's good practice to use "Git: Clone(Recursive)" instead of just "Git(Clone)" because cloning the repo recursively also clones all the defined submodules in the repo as well.

!!! tip
    By convention, commit names are in simple present tense. Things like `add name` or `fix these bugs` or `update readme`. You are expected to write descriptive commit messages that follow this convention.
