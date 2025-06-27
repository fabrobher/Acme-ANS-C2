
package acme.entities.services;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.datatypes.Money;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoney;
import acme.client.components.validation.ValidString;
import acme.client.components.validation.ValidUrl;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "service")
public class Service extends AbstractEntity {

	//Serialisation version -----------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ---------------------------------------------------------------
	@Mandatory
	@ValidString(max = 50, min = 1)
	@Column(unique = true)
	@Automapped
	private String				name;

	@Mandatory
	@ValidUrl
	@Size(max = 255)
	@Automapped
	private String				pictureUrl;

	@Mandatory
	@Positive
	@Automapped
	private Double				averageDwellTime;

	@Optional
	@ValidString(pattern = "^[A-Z]{4}-[0-9]{2}$")
	@Automapped

	private String				promotionCode;

	@Optional
	@Automapped
	@ValidMoney
	private Money				discountAmount;

}
