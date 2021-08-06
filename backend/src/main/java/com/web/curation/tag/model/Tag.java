package com.web.curation.tag.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "tags")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tagcode")
	private Integer tagcode;

	@Column(name = "tagname")
	private String tagname;

	@Column(name = "cnt")
	private Integer cnt;

	public Integer getTagcode() {
		return tagcode;
	}

	public void setTagcode(Integer tagcode) {
		this.tagcode = tagcode;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

}
