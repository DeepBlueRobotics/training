## uv

uv is a python package manager that simplifies the process of installing dependencies and running development servers.

### Installing uv
If you do not have uv installed, go to [the uv documentation](https://docs.astral.sh/uv/getting-started/installation/) and follow the instructions for your OS. This will also install Python 3 onto your machine.

### Source Code
Clone the [training docs repo](https://github.com/DeepBlueRobotics/training>) from Github, with your preferred method.

### Python Dependencies
This site uses the MkDocs Python package to convert Markdown files to a website, and a few more packages to provide additional functionality and theming. You can install all the necessary dependencies by running `uv sync` in your terminal while in the root directory of this repo.


## Development Workflow
To develop locally, run `uv run -m mkdocs serve` in the root directory of this project. This will generate a local website.

The terminal will output a line something like __this below__, where you can find the link of your local website.

`INFO     -  [19:08:41] Serving on http://127.0.0.1:8000/`

When you are done making a change, simply create a new branch of <https://github.com/DeepBlueRobotics/training> and make a pull request. ReadTheDocs will automatically update the site within a few minutes, if your pull request is accepted.