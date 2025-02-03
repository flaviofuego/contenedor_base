# Use the official Python image from the Docker Hub
FROM python:3.9-slim

WORKDIR /python

# Copy the current directory contents into the container at /app
COPY . /python

# Install any needed packages specified in requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Run app.py when the container launches
CMD ["python", "app.py"]

# Mount the volume
VOLUME ["/output.csv"]