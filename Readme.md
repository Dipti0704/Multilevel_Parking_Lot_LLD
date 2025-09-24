# Parking Lot System (LLD)

This project is an implementation of a **Parking Lot Management System** using Object-Oriented Programming principles, SOLID principles, and Design Patterns.  
It simulates real-world parking lot operations like parking, slot allocation, billing, electric vehicle charging, and payment processing.

---

## 🚗 Features
- Supports multiple vehicle types:
  - Motorcycle
  - Car
  - Truck
  - Electric Car / Electric Motorcycle
- Multiple slot types (`MOTORCYCLE`, `CAR`, `TRUCK`) with size-based fit checks.
- Charging stations support for electric vehicles.
- Ticket-based parking with entry and exit times.
- Multiple pricing strategies:
  - Hourly-based pricing
  - Flat-rate pricing
- Multiple slot allocation strategies:
  - Nearest available slot
  - Random allocation
- Multiple payment methods:
  - Cash
  - Card
  - Digital Wallets
- Displays availability & occupancy per slot type and charging stations.

---

## 🏗️ Design Patterns Used
- **Strategy Pattern**  
  - For pricing (`PricingStrategy` → `HourlyPricingStrategy`, `FlatPricingStrategy`)  
  - For slot allocation (`SlotAllocationStrategy` → `NearestSlotStrategy`, `RandomSlotStrategy`)  
- **Open-Closed Principle (OCP)**  
  - New strategies or payment methods can be added without modifying existing logic.  
- **Single Responsibility Principle (SRP)**  
  - Each class handles only one responsibility (e.g., `ParkingSlot` manages slot state, `Ticket` manages ticket details).  
- **Polymorphism**  
  - Payment processing (`PaymentMethod`) and vehicles (`Vehicle` subclasses).  

---

