# Use the official Python image from the Docker Hub
FROM python:3.9-slim

WORKDIR /app

# Copy the current directory contents into the container at /app
COPY main.py .

#RUN mkdir data
# Run app.py when the container launches
CMD ["python", "main.py"]

# Mount the volume
#VOLUME ["/output.csv"]