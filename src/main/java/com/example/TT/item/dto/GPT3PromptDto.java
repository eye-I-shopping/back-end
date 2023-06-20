package com.example.TT.item.dto;

public class GPT3PromptDto {
	String model;
	String prompt;
	int max_tokens;
	double temperature;
	double top_p;
	int n;
	boolean stream;
	String logprobs;
	String stop;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public int getMax_tokens() {
		return max_tokens;
	}

	public void setMax_tokens(int max_tokens) {
		this.max_tokens = max_tokens;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getTop_p() {
		return top_p;
	}

	public void setTop_p(double top_p) {
		this.top_p = top_p;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public boolean isStream() {
		return stream;
	}

	public void setStream(boolean stream) {
		this.stream = stream;
	}

	public String getLogprobs() {
		return logprobs;
	}

	public void setLogprobs(String logprobs) {
		this.logprobs = logprobs;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}
}