package top.oldwei.demo.neo4j.test;

import java.util.List;

public abstract class DocHtmlDto {

	private String html;
	private List<String> imageList;

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

}
