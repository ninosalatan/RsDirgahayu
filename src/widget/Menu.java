package widget;

import java.util.List;

public class Menu {
	private String id;
	private String parentId;
	private String text;

	private String icons;
	private List<Menu> children;

	public Menu(String id, String parentId, String text, String icons) {
		this.id = id;
		this.parentId = parentId;
		this.text = text;
		this.icons = icons;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcons() {
		return icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
}
