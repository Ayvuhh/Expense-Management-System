package model;

import model.Category;

import java.io.Serializable;
import java.time.LocalDate;

public record Expense(double amount,Currency currency, Category category, LocalDate date) implements Serializable {

}