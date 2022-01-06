package de.agiehl.bgg.plays.model.bgg;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Subtype {

	@JacksonXmlProperty(isAttribute = true)
	private String value;

}
