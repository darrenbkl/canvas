# Canvas


## Running the project
```
cd canvas
mvn clean package
java -jar target/canvasapp-1.0.0-shaded.jar
```

## Assumptions
- Canvas size is limited to 1000 x 1000.
- User is able to restart by creating new canvas.
- A line must be vertical or horizontal. The points that form a line must be different.
- The points that form a rectangle must be different. 
- A line is not a rectangle.