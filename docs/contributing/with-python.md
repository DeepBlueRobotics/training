## Installing Software

### Python 3
If you do not have Python installed, download it from the official website and follow its install instructions: <https://www.python.org/downloads/>

### Source Code
Clone the training docs repo: <https://github.com/DeepBlueRobotics/training>

### Python Dependencies
This site uses the MkDocs Python package to convert Markdown files to a website, and a few more packages to provide additional functionality and theming. You can install all the necessary dependencies by running `python3 -m pip install -r requirements.txt` in terminal while in the root directory of this repo.

## Development Workflow
To develop locally, run `python3 -m mkdocs serve` in the root directory of this project. This will generate a local website which you can find in the terminal after Serving on and autorefresh whenever changes are made. 

It will be in something like this:

`INFO     -  [19:08:41] Serving on http://127.0.0.1:8000/`

When you are done making a change, simply push/merge it to the master branch of <https://github.com/DeepBlueRobotics/training>. ReadTheDocs will automatically update the site within a few minutes.

