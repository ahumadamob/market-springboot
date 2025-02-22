package com.ahumadamob.market.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class AdjustmentConfig extends BaseEntity {

    @NotBlank(message = "Nombre no puede estar vacío")
    @Size(max = 100, message = "Nombre no puede exceder los 100 caracteres")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Debe completar el tipo de ajuste")
    private AdjustmentType type;

    @NotNull(message = "Valor no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor debe ser positivo")
    private BigDecimal value;

    private boolean percentage;
    private boolean addition;

    @NotNull(message = "Fecha de comienzo no puede estar vacío")
    private LocalDate startDate;

    private LocalDate endDate;

    private boolean displayOnPage;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdjustmentType getType() {
		return type;
	}

	public void setType(AdjustmentType type) {
		this.type = type;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public boolean isPercentage() {
		return percentage;
	}

	public void setPercentage(boolean percentage) {
		this.percentage = percentage;
	}

	public boolean isAddition() {
		return addition;
	}

	public void setAddition(boolean addition) {
		this.addition = addition;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isDisplayOnPage() {
		return displayOnPage;
	}

	public void setDisplayOnPage(boolean displayOnPage) {
		this.displayOnPage = displayOnPage;
	}

	@PrePersist
    @PreUpdate
    private void validateDates() {
        if (endDate != null && startDate != null && !startDate.isBefore(endDate)) {
            throw new IllegalArgumentException("Fecha de inicio debe ser anterior a la fecha de fin");
        }
    }
    
    public BigDecimal applyAdjustment(BigDecimal basePrice) {
        BigDecimal adjustmentAmount;
        if (percentage) {
            adjustmentAmount = basePrice.multiply(value).divide(BigDecimal.valueOf(100));
        } else {
            adjustmentAmount = value;
        }
        return addition ? basePrice.add(adjustmentAmount) : basePrice.subtract(adjustmentAmount);
    }
    
   public boolean isActiveOn(LocalDate date) {
        return (startDate == null || !date.isBefore(startDate)) &&
               (endDate == null || !date.isAfter(endDate));
    } 


}

