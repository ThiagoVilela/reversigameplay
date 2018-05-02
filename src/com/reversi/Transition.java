package com.reversi;

import java.util.ArrayList;

public class Transition {
	public ArrayList<Coordinate> initial = new ArrayList<Coordinate>();
	public ArrayList<Coordinate> end = new ArrayList<Coordinate>();
	public ArrayList<Coordinate> direction = new ArrayList<Coordinate>();
	public ArrayList<Integer> numberOfIterations = new ArrayList<Integer>();
	public ArrayList<Integer> pointsAdd = new ArrayList<Integer>();
	public ArrayList<Integer> pointsRemove = new ArrayList<Integer>();
}
