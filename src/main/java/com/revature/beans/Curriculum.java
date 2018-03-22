package com.revature.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Bean for the Curriculum table
 * 
 * @author JIAQI ZHANG
 *
 */
@Entity
@Table(name = "HYDRA_CURRICULUM")
@Cacheable
public class Curriculum implements Serializable {

	private static final long serialVersionUID = 2803643371501250761L;

	@Id
	@Column(name = "CURRICULUM_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRICULUM_ID_SEQUENCE")
	@SequenceGenerator(name = "CURRICULUM_ID_SEQUENCE", sequenceName = "CURRICULUM_ID_SEQUENCE")
	private Integer curriculumId;

	@Column(name = "CURRICULUM_NAME")
	private String curriculumName;

	public Curriculum() {
		super();
	}

	public Curriculum(String curriculumName) {
		super();
		this.curriculumName = curriculumName;
	}

	public Curriculum(Integer curriculumId, String curriculumName) {
		super();
		this.curriculumId = curriculumId;
		this.curriculumName = curriculumName;
	}

	public Integer getCurriculumId() {
		return curriculumId;
	}

	public void setCurriculumId(Integer curriculumId) {
		this.curriculumId = curriculumId;
	}

	public String getCurriculumName() {
		return curriculumName;
	}

	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curriculumId == null) ? 0 : curriculumId.hashCode());
		result = prime * result + ((curriculumName == null) ? 0 : curriculumName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curriculum other = (Curriculum) obj;
		if (curriculumId == null) {
			if (other.curriculumId != null)
				return false;
		} else if (!curriculumId.equals(other.curriculumId))
			return false;
		if (curriculumName == null) {
			if (other.curriculumName != null)
				return false;
		} else if (!curriculumName.equals(other.curriculumName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curriculum [curriculumId=" + curriculumId + ", curriculumName=" + curriculumName + "]";
	}

}
