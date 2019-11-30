## Docker Desktop

Docker eliminates the hassle of managing and installing software on different machines. You can find it here: <https://www.docker.com/products/docker-desktop>

## Source Code

Clone the training docs repo: <https://github.com/DeepBlueRobotics/training>

## Developmental Workflow

To develop locally, run `docker run --rm -it -p 8000:8000 -v ${PWD}:/docs squidfunk/mkdocs-material` in the root directory of this project. This will generate a local website from the code at <http://localhost:8000> and autorefresh whenever changes are made. 

When you are done making a change, simply push/merge it to the master branch of <https://github.com/DeepBlueRobotics/training>. ReadTheDocs will automatically update the site within a few minutes.

