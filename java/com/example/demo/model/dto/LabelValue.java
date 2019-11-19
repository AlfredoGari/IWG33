package com.example.demo.model.dto;

public class LabelValue {
	private String label;
	private double value;

	public LabelValue(String label, double value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
