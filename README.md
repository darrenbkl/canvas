# Canvas


## Running the project
```
cd canvas
mvn clean package
java -jar target/canvasapp-1.0.0-shaded.jar
```

## Assumptions
- User is able to restart by creating new canvas.
- When drawing line and rectangle,  Point 2 (x2, y2) must be larger than Point 1 (x1, y1). In another words, x2 > x1 && y2 > y1 must be fulfilled.
- A line must be vertical or horizontal. The points that form a line must be different on the canvas.
- The points that form a rectangle must be different on the canvas. A line is not considered as a rectangle.
- User can fill anywhere, that includes any empty space on canvas, or on a line/rectangle.