package de.agiehl.bgg.plays.model.bgg;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import lombok.Data;

@Data
public class Comments {

	@JacksonXmlText
	private String text;

}
