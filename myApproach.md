  Requirement Gathering::::::

  1. Parking lot has multiple floors and each floor has parking slot
  2. Parking lot has multiple entry and exit (all on ground floor)
  3. customer collects the ticket from the entry gate . ticket has the entry time and exit time and slot number
  4. customers to make payment while exit
  5. If the parking lot is full then customers will be denied entry
  6. Parking lot has parking of different types for different size vehicle like car, bus, bike.
  7. some parking lots has power plugs for e-vechicle.




My Approach – Multilevel Parking Lot System
-------------------------------------------

My Approach – Multilevel Parking Lot System
-------------------------------------------

So when I started thinking about this parking lot problem, my first observation was 
that the system has to be scalable… like it should handle multiple floors, different 
vehicles, tickets, entry and exit etc. So I thought of breaking the whole thing into 
small parts.

I think vehicles should be an abstract concept, because car, bike, truck, electric car 
all share some common fields but also have their own differences. That’s why I made a 
Vehicle base class and then extended it into Car, Motorcycle, Truck, ElectricCar. 
I also felt electric cars need special handling because of charging, so I added that 
separately.

Then my observation was slots and floors are the core of parking. Each slot should 
have its own ID like F1-C001 so that it directly tells me the floor and type. 
I made SlotType enum so that it can decide what vehicle can fit inside. 
I think this helps to keep validation simple.

For parking flow, I thought of entry and exit. On entry, a slot must be chosen. 
But then I realized, sometimes we want nearest slot, sometimes random… 
so I made SlotAllocationStrategy interface. This way I can switch strategies anytime. 
Similarly for payment, I thought in real life we don’t always pay hourly, 
sometimes it’s flat rate, so I made PricingStrategy. 

I think using Strategy Pattern here makes sense because it allows flexibility. 
Also, for payments, I made CashPayment, CardPayment, DigitalPayment. 
Again this is strategy pattern because I can plug and play. 
This way ParkingLot is not directly dependent on cash or card, 
it only knows PaymentMethod.

When I designed Ticket, I thought it should auto generate ID and store entry time, 
exit time, vehicle details etc. And when vehicle exits, it calculates charges 
using whichever PricingStrategy is active. Then payment is processed and 
slot is freed.

On SOLID side, my observation is:
- Each class is doing only one thing (SRP).
- I can add new vehicle or new payment method without touching existing code (OCP).
- Subclasses like Car, Truck easily replace Vehicle (LSP).
- I separated interfaces for strategy instead of one big interface (ISP).
- ParkingLot depends on interfaces not concrete classes (DIP).

So in short, my approach was: break system into vehicles, slots, tickets, strategies 
and payments. Keep everything extendable. Use strategy pattern where flexibility is 
needed. And keep Ticket + ParkingLot as the managers that control flow.

I think this design makes the parking lot clean, modular, and easy to extend 
for future requirements like reservations or electric charging stations.




1. Problem Understanding
------------------------
The task was to design and implement a multilevel parking lot system with the following key requirements:
- Support multiple floors and slot types (Motorcycle, Car, Truck).
- Handle electric vehicles with charging stations.
- Allow different allocation strategies (nearest slot, random slot).
- Allow different pricing strategies (hourly, flat).
- Manage entry, exit, tickets, and payments.
- Follow SOLID principles and design patterns.

2. High-Level Design
--------------------
I broke down the system into several components:

- Enums:
  - SlotType → Defines slot categories and their size.
  - VehicleType → Defines vehicle categories.

- Vehicles:
  Abstract class Vehicle with concrete classes (Car, Motorcycle, Truck, ElectricCar).
  - Electric vehicles track charging usage.

- Core Models:
  - ParkingLot → Manages slots, tickets, strategies, and operations.
  - ParkingSlot → Represents a single slot with details (floor, type, charging).
  - Ticket → Records entry/exit, time, amount, and payment status.

- Payments:
  Strategy interface PaymentMethod with implementations: CashPayment, CardPayment, DigitalPayment.

- Strategies:
  - SlotAllocationStrategy → (NearestSlotStrategy, RandomSlotStrategy).
  - PricingStrategy → (HourlyPricingStrategy, FlatPricingStrategy).

- Main Driver:
  Demonstrates end-to-end operations with vehicles, exits, pricing, allocation, and full parking scenarios.

3. Detailed Approach
--------------------

3.1 Slot & Vehicle Management
- Each slot is identified by a unique ID (F1-C001) which encodes floor + type + number.
- SlotType.canFit() ensures correct vehicle-to-slot mapping.
- Electric vehicles require charging slots. The system prevents wrong allocations.

3.2 Parking & Ticketing
- On entry, the ParkingLot:
  1. Filters available slots.
  2. Uses the current allocation strategy to choose the slot.
  3. Creates a Ticket with auto-incremented ID.
  4. Marks the slot as occupied and assigns the vehicle.

- On exit, the ParkingLot:
  1. Retrieves ticket & slot.
  2. Calculates price using the active pricing strategy.
  3. Processes payment via chosen PaymentMethod.
  4. Frees the slot if payment succeeds.

3.3 Strategy Pattern
- Slot Allocation Strategy:
  - NearestSlotStrategy → Chooses lowest floor, smallest ID.
  - RandomSlotStrategy → Random allocation.
  (Changeable at runtime → Strategy Design Pattern).

- Pricing Strategy:
  - HourlyPricingStrategy → Price = hours × rate (+charging).
  - FlatPricingStrategy → Fixed base + optional charging.
  (Changeable at runtime → Strategy Design Pattern).

3.4 Payment Handling
- Implemented via Strategy Pattern (PaymentMethod).
- Supports: Cash, Card, Digital wallets.
- Extensible without modifying ParkingLot.

3.5 Multi-Level Support
- Each slot stores its floor number.
- Allocation strategies can prioritize lower floors (nearest) or choose randomly.
- System prints availability per floor and type.

3.6 Extensibility
- Adding a new vehicle type → extend Vehicle.
- Adding a new slot allocation/pricing → implement respective strategy interface.
- Adding a new payment → implement PaymentMethod.
(Open/Closed Principle).

4. SOLID Principles Applied
---------------------------
- SRP: Each class has one clear role (Vehicle, Ticket, ParkingSlot, PricingStrategy).
- OCP: Strategies & payments can be extended without modifying base logic.
- LSP: Any Vehicle subclass works in ParkingSlot & ParkingLot.
- ISP: Separate strategy interfaces for pricing & allocation.
- DIP: ParkingLot depends on abstractions, not implementations.

5. Design Patterns Used
-----------------------
1. Strategy Pattern → For slot allocation, pricing, and payments.
2. Factory-like Ticketing → Ticket auto-generates IDs internally.
3. Encapsulation → Vehicle, Slot, and Ticket details hidden via getters/setters.

6. Example Flow
---------------
1. Car enters → allocated nearest slot on Floor 1 → Ticket generated.
2. Motorcycle enters → allocated random slot (if strategy switched).
3. Electric car enters → assigned only to slot with charging.
4. On exit → Ticket calculates price → Payment processed → Slot freed.
5. ParkingLot prints availability and active tickets.

7. Conclusion
-------------
This design successfully handles a scalable, multilevel parking lot system with:
- Multiple vehicle types & slots.
- Electric charging integration.
- Flexible runtime strategies (allocation, pricing).
- Extensible payment options.
- Clean adherence to SOLID + Strategy Design Pattern.

The implementation ensures maintainability, extensibility, and real-world alignment.
