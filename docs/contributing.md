There are always going to be improvements, fixes, and updates that can be made to our training material. Detailed on this page is how you can help with these efforts.

## Installing Software

### Python 3
If you do not have Python installed, download it from the official website and follow its install instructions: <https://www.python.org/downloads/>

### Source Code
The training material source code is located at <https://github.com/DeepBlueRobotics/training>. Clone this repo and navigate to it.

### Python Dependencies
This site uses the MkDocs Python package to convert Markdown files to a website, and a few more packages to provide additional functionality and theming. You can install all the necessary dependencies by runnin `pip install -r requirements.txt` while in the root directory of this repo.

## Development Workflow
To develop locally, run `mkdocs serve` in the root directory of this project. This will generate a local website from the code and autorefresh whenever changes are made. 

When you are done making a change, simply push/merge it to the master branch of <https://github.com/DeepBlueRobotics/training>. ReadTheDocs will automatically update the site within a few minutes.

## File Structure
Here are some conventions that this project follow:

1. The directory structure of the source code corresponds to the navigation structure of the site. That means that all the files in one directory are also all part of one section of the site. 
2. For every numbered section, there is an intro page, additional exercises page, and content pages in between.
    1. The intro page contains an "Essential Question" that the section attempts to answer, along with a brief description of what will be covered in this section.
    3. The additional exercises page is for optional/challenge problems that apply the content of the section and sections before it. Answers/solutions are hidden under dropdowns that show when you click on them.

!!! question "Essential Question"
    Example question?

??? example "Solution"
    Example solution.

## Resources

### Markdown
The entire site is based on Markdown. I've found this cheatsheet to be very useful for writing Markdown: <https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet>

### MkDocs 
The site is generated with MkDocs, which converts the written Markdown to a fully fleshed website. You can find more information about MkDocs here: <https://www.mkdocs.org/>

### ReadTheDocs
This site is hosted on ReadTheDocs, which also handles the conversion of Markdown to HTML with MkDocs. The homepage for ReadTheDocs is located here: <https://readthedocs.org/>. I suggest checking the Getting Started Guide for information about how to use this service.

### Material Theme
This site uses the Material Design theme for MkDocs. You can find more information about the Material theme here: <https://squidfunk.github.io/mkdocs-material/>

### Markdown Extensions
In addition to default Markdown, the site also uses some extensions to provide additinal functionality. All the enabled extensions can be found in the `markdown_extensions` list in `mkdocs.yml`. 

Currently, information about all the extensions we use can be found here: <https://squidfunk.github.io/mkdocs-material/extensions/admonition/>

If you add an extension that requires a new Python package to be installed, be sure to add that package to `requirements.txt`.