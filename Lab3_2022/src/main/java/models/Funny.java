package models;
import java.sql.Timestamp;

public class Funny implements java.io.Serializable {

	 private static final long serialVersionUID = 1L;

	 private int id;
	 private int parentId;
	 private int authorId;
	 private String content;
	 private Timestamp timestamp;

	 public Funny() {
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}



}