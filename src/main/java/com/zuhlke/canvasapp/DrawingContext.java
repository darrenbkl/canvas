package com.zuhlke.canvasapp;

import com.zuhlke.canvasapp.drawable.Canvas;

public interface DrawingContext {

    Canvas getCanvas();

    void exit();
}