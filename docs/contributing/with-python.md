## Installing Software

!!! warning
    To contribute to the website, you will need to use the terminal/command line on your computer. This is **not** a user-friendly application, so if it's your first time - get a veteran who's familiar with it.

### Python 3
If you do not have Python installed, follow the instructions to download it for your computer's OS (typically Windows, OSX/MacOS, or Linux) from [this website](https://realpython.com/installing-python/).

### Source Code
Clone the [training docs repo](https://github.com/DeepBlueRobotics/training>) with Github, with your preferred method.

### Python Dependencies
This site uses the MkDocs Python package to convert Markdown files to a website, and a few more packages to provide additional functionality and theming. You can install all the necessary dependencies by run in terminal `python3 -m pip install -r requirements.txt` while in the root directory of this repo.

## Development Workflow
To develop locally, run `python3 -m mkdocs serve` in the root directory of this project. This will generate a local website which autorefreshes whenever changes are made. 

The terminal will output a line something like __this below__, where you can find the link of your local website.

`INFO     -  [19:08:41] Serving on http://127.0.0.1:8000/`

When you are done making a change, simply create a new branch of <https://github.com/DeepBlueRobotics/training> and make a pull request. ReadTheDocs will automatically update the site within a few minutes, if your pull request is accepted.

