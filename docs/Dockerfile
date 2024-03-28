# Starting from the Python 3.8 image
FROM python:3.8-slim

# Create a directory to hold the application
WORKDIR /app

COPY . /app

# Install the mkdocs and Material for MkDocs package
RUN pip install mkdocs
RUN pip install mkdocs-material
RUN pip install neoteroi-mkdocs

# Indicate which port to expose
EXPOSE 8000

# Start the MkDocs server
CMD ["mkdocs", "serve", "--dev-addr=0.0.0.0:8000"]