

/******************************************************************************
 *  Description: This data-driven program simulates motion in the universe defined
 *  by the standard input stream, increasing time at the rate on the
 *  command line.
 *
 *  Execution:    java Universe dt "input.txt"
 *
 *  %  java Universe 25000 "data/nbody/planets.txt"
 *
 *
 ******************************************************************************/

public class Universe {
    private final int n;             // number of bodies
    private final Body[] bodies;     // array of n bodies

    // read universe from file
    public Universe(String filename) {

	// TODO: Use BufferedReader to open the file

        // TODO: read number of bodies

        // the set scale for drawing on screen
        double radius = ______;
        StdDraw.setXscale(-radius, +radius); 
        StdDraw.setYscale(-radius, +radius); 

        // read in the n bodies
        bodies = new Body[n]; 
        for (int i = 0; i < n; i++) { 
            double rx   = ______;
            double ry   = ______;
            double vx   = ______;
            double vy   = ______;
            double mass = ______;
            double[] position = { rx, ry }; 
            double[] velocity = { vx, vy }; 
            SpatialVector r = new SpatialVector(position); 
            SpatialVector v = new SpatialVector(velocity); 
            bodies[i] = new Body(r, v, mass); 
        } 
    } 

    // increment time by dt units, assume forces are constant in given interval
    public void increaseTime(double dt) {

        // initialize the forces to zero
        SpatialVector[] f = new SpatialVector[n];
        for (int i = 0; i < n; i++) {
            f[i] = new SpatialVector(new double[2]);
        }

        // compute the forces and apply principle of superposition
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    f[i] = f[i].plus(bodies[i].forceFrom(bodies[j]));
                }
            }
        }

        // move the bodies
        for (int i = 0; i < n; i++) {
            bodies[i].move(f[i], dt);
        }
    }

    // draw the n bodies
    public void draw() {
        for (int i = 0; i < n; i++) {
            bodies[i].draw();
        }
    } 


    // client to simulate a universe
    public static void main(String[] args) {
        double dt = Double.parseDouble(args[0]);
        Universe newton = new Universe(args[1]);
        StdDraw.enableDoubleBuffering();
        while (true) {
            StdDraw.clear(); 
            newton.increaseTime(dt); 
            newton.draw(); 
            StdDraw.show();
            StdDraw.pause(10);
        } 
    } 
}
