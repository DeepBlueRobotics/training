## Markdown
The entire site is based on Markdown. I've found this cheatsheet to be very useful for writing Markdown: <https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet>

## MkDocs 
The site is generated with MkDocs, which converts the written Markdown to a fully fleshed website. You can find more information about MkDocs here: <https://www.mkdocs.org/>

## ReadTheDocs
This site is hosted on ReadTheDocs, which also handles the conversion of Markdown to HTML with MkDocs. The homepage for ReadTheDocs is located here: <https://readthedocs.org/>. I suggest checking the Getting Started Guide for information about how to use this service.

## Material Theme
This site uses the Material Design theme for MkDocs. You can find more information about the Material theme here: <https://squidfunk.github.io/mkdocs-material/>

## Markdown Extensions
In addition to default Markdown, the site also uses some extensions to provide additinal functionality. All the enabled extensions can be found in the `markdown_extensions` list in `mkdocs.yml`. 

Currently, information about all the extensions we use can be found here: <https://squidfunk.github.io/mkdocs-material/extensions/admonition/>

If you add an extension that requires a new Python package to be installed, be sure to add that package to `requirements.txt`.