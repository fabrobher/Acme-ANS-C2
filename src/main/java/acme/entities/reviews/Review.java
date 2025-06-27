
package acme.entities.reviews;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidNumber;
import acme.client.components.validation.ValidString;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review")

public class Review extends AbstractEntity {

	// Serialisation version

	private static final long	serialVersionUID	= 1L;

	// Attributes

	@Mandatory
	@ValidString(min = 1, max = 50)
	@Automapped
	private String				alias;

	@Mandatory
	@ValidMoment(past = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	@Mandatory
	@ValidString(min = 1, max = 50)
	@Automapped
	private String				subject;

	@Mandatory
	@ValidString(min = 1, max = 50)
	@Automapped
	private String				text;

	@Optional
	@ValidNumber(min = 0, max = 10)
	@Automapped
	private Double				score;

	@Optional
	@Valid
	@Automapped
	private Boolean				isRecommended;

}
