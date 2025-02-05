# Use the official Python image from the Docker Hub
FROM python:3.9-slim

WORKDIR /python

# Copy the current directory contents into the container at /app
COPY main.py .

# Run app.py when the container launches
CMD ["python", "app.py"]

# Mount the volume
#VOLUME ["/output.csv"]