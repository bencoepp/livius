package bencoepp.livius.entities.trade;

import bencoepp.livius.entities.state.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.util.List;

/**
 * The Trade class represents a trade document in the "trades" collection of a MongoDB database.
 * It contains various properties that describe the characteristics of a trade.
 * <p>
 * This class is annotated with the following annotations:
 * - @Getter: Generates getter methods for all non-static fields of the class.
 * - @Setter: Generates setter methods for all non-static fields of the class.
 * - @NoArgsConstructor: Generates a no-argument constructor for the class.
 * - @AllArgsConstructor: Generates a constructor with arguments for all fields of the class.
 * - @Document(collection = "trades"): Specifies that objects of this class will be stored in the "trades" collection
 *   in the MongoDB database.
 * <p>
 * The Trade class has the following fields:
 * - id: A String field representing the trade ID.
 * - year: An Integer field representing the year of the trade.
 * - importerA: A List<State> field representing the importing states from group A.
 * - importerB: A List<State> field representing the importing states from group B.
 * - importerAFlow: A Double field representing the import flow from importer A.
 * - importerBFlow: A Double field representing the import flow from importer B.
 * - sourceImporterAFlow: A String field representing the source of importer A flow.
 * - sourceImporterBFlow: A String field representing the source of importer B flow.
 * - originalPRCTradeValuesImporterA: A String field representing the original PRC trade values for importer A.
 * - originalPRCTradeValuesImporterB: A String field representing the original PRC trade values for importer B.
 * - originalLUXTradeValuesImporterA: A String field representing the original LUX trade values for importer A.
 * - originalLUXTradeValuesImporterB: A String field representing the original LUX trade values for importer B.
 * <p>
 * Example usage:
 * <pre>{@code
 * Trade trade = new Trade();
 * trade.setId("1");
 * trade.setYear(2022);
 * trade.setImporterA(importerAStates); // Assuming importerAStates is a List<State> object
 * trade.setImporterB(importerBStates); // Assuming importerBStates is a List<State> object
 * trade.setImporterAFlow(100.0);
 * trade.setImporterBFlow(200.0);
 * trade.setSourceImporterAFlow("Source A");
 * trade.setSourceImporterBFlow("Source B");
 * trade.setOriginalPRCTradeValuesImporterA("PRC Trade A");
 * trade.setOriginalPRCTradeValuesImporterB("PRC Trade B");
 * trade.setOriginalLUXTradeValuesImporterA("LUX Trade A");
 * trade.setOriginalLUXTradeValuesImporterB("LUX Trade B");
 * }</pre>
 *
 * @see State
 * @see Trade#id
 * @see Trade#year
 * @see Trade#importerA
 * @see Trade#importerB
 * @see Trade#importerAFlow
 * @see Trade#importerBFlow
 * @see Trade#sourceImporterAFlow
 * @see Trade#sourceImporterBFlow
 * @see Trade#originalPRCTradeValuesImporterA
 * @see Trade#originalPRCTradeValuesImporterB
 * @see Trade#originalLUXTradeValuesImporterA
 * @see Trade#originalLUXTradeValuesImporterB
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "trades")
public class Trade {
    @Transient
    public static final String SEQUENCE_NAME="trade_seq";
    @Id
    private String id;
    /**
     * Represents a year value.
     * <p>
     * This variable is of type Integer and holds the value of a year.
     * It is recommended to use the getter and setter methods provided by the containing class to access or modify the value of this variable.
     * <p>
     * Example usage:
     * <pre>{@code
     * Trade trade = new Trade();
     * trade.setYear(2022);
     * Integer year = trade.getYear();
     * }</pre>
     *
     * @see Trade
     * @see Trade#getYear()
     * @see Trade#setYear(Integer)
     */
    private Integer year;
    /**
     * The importerA variable represents a list of State objects.
     * <p>
     * It is a private instance variable within the Trade class. The Trade class contains various properties and methods related to trade data.
     * <p>
     * The State class represents a state document in a MongoDB database. It contains properties that describe the characteristics of a state.
     * <p>
     * The importerA variable is annotated with @DocumentReference, indicating that it refers to a field in a MongoDB document.
     * <p>
     * Example usage:
     * // Create a new Trade object
     * Trade trade = new Trade();
     * <p>
     * // Get the value of importerA
     * List<State> importerA = trade.getImporterA();
     * <p>
     * // Iterate over the elements in importerA
     * for (State state : importerA) {
     *     // Access properties of the State object
     *     System.out.println(state.getCode());
     *     System.out.println(state.getName());
     *     // ...
     * }
     *
     * @see Trade
     * @see Trade#getImporterA()
     */
    @DocumentReference
    private List<State> importerA;
    /**
     * The importerB variable represents a list of State objects.
     * <p>
     * This variable is a private instance variable within the containing class Trade.
     * It holds a list of State objects that represent the importers associated with a trade.
     * <p>
     * The importerB variable is annotated with @DocumentReference to indicate that it refers to a field in a MongoDB document.
     * The field it refers to is "importerB" in the "trades" collection.
     * <p>
     * A State object represents a state document in a MongoDB database and contains various properties that describe the characteristics of a state.
     * <p>
     * Example usage:
     * <pre>{@code
     * Trade trade = new Trade();
     * List<State> importerB = trade.getImporterB();
     *
     * for (State state : importerB) {
     *     System.out.println(state.getCode());
     *     System.out.println(state.getCowId());
     *     System.out.println(state.getName());
     *     System.out.println(state.getStartDate());
     *     System.out.println(state.getEndDate());
     *     // ...
     * }
     * }</pre>
     *
     * @see Trade
     * @see State
     * @see State#getCode()
     * @see State#getCowId()
     * @see State#getName()
     * @see State#getStartDate()
     * @see State#getEndDate()
     */
    @DocumentReference
    private List<State> importerB;
    /**
     * Represents the A flow of Importer in Trade.
     * <p>
     * The importerAFlow variable is a private Double field in the Trade class.
     * It is used to store the A flow of the Importer in a Trade.
     * <p>
     * Example usage:
     * <pre>{@code
     * Trade trade = new Trade();
     * trade.setImporterAFlow(100.0);
     * Double importerAFlow = trade.getImporterAFlow();
     * }</pre>
     *
     * @see Trade
     * @see Trade#getImporterAFlow()
     * @see Trade#setImporterAFlow(Double)
     */
    private Double importerAFlow;
    /**
     * The importerBFlow variable represents the flow of importer B in the Trade class.
     * <p>
     * This variable is a private Double field in the Trade class. It is used to store the flow value associated with importer B.
     * The flow value represents the amount or quantity of goods or services imported by importer B.
     * <p>
     * This variable is marked as private, so it cannot be directly accessed or modified outside the Trade class.
     * It is recommended to use the getter and setter methods provided by the Trade class to access or modify the value of this variable.
     * <p>
     * Example usage:
     * <pre>{@code
     * Trade trade = new Trade();
     * trade.setImporterBFlow(1000.0);
     * Double flow = trade.getImporterBFlow();
     * }</pre>
     *
     * @see Trade
     * @see Trade#getImporterBFlow()
     * @see Trade#setImporterBFlow(Double)
     */
    private Double importerBFlow;
    /**
     * The sourceImporterAFlow variable represents the source importer A flow associated with a Trade object.
     * <p>
     * This variable is a private instance variable within the Trade class and is of type String.
     * It holds the value of the source importer A flow for a specific trade.
     * <p>
     * The source importer A flow represents the flow of goods or services from the source importer A in a trade transaction.
     * It provides information about the quantity, value, and other characteristics of the goods or services being imported.
     * <p>
     * It is recommended to use the getter and setter methods provided by the Trade class to access or modify the value of this variable, as it is marked as private.
     * <p>
     * Example usage:
     * <pre>{@code
     * Trade trade = new Trade();
     * trade.setSourceImporterAFlow("Flow123");
     * String sourceImporterAFlow = trade.getSourceImporterAFlow();
     * }</pre>
     *
     * @see Trade
     * @see Trade#getSourceImporterAFlow()
     * @see Trade#setSourceImporterAFlow(String)
     */
    private String sourceImporterAFlow;
    /**
     * The sourceImporterBFlow variable represents a private String field in the Trade class.
     * <p>
     * It is used to store the code associated with the importer B flow for a specific trade object.
     * The importer B flow represents the flow of imports for a particular trade.
     * <p>
     * Example usage:
     * <pre>{@code
     * Trade trade = new Trade();
     * trade.setSourceImporterBFlow("ABC123");
     * String sourceImporterBFlow = trade.getSourceImporterBFlow();
     * }</pre>
     *
     * @see Trade
     * @see Trade#getSourceImporterBFlow()
     * @see Trade#setSourceImporterBFlow(String)
     */
    private String sourceImporterBFlow;
    /**
     * Represents a private variable in the Trade class.
     * <p>
     * This variable is of type String and holds a value that is not accessible outside the class it belongs to.
     * It is used to store the original PRC trade values for importerA.
     * <p>
     * It is recommended to use the getter and setter methods provided by the class to access or modify the value of this variable, as it is marked as private.
     *
     * @see Trade
     * @see Trade#getOriginalPRCTradeValuesImporterA()
     * @see Trade#setOriginalPRCTradeValuesImporterA(String)
     */
    private String originalPRCTradeValuesImporterA;
    /**
     * Represents the originalPRCTradeValuesImporterB variable in the Trade class.
     * <p>
     * This variable is a private instance variable that holds the original PRCTrade values for the B importer.
     * It is of type String and is not accessible outside the class it belongs to.
     * It is used to store the trade values associated with the B importer for a specific trade object.
     * <p>
     * It is recommended to use the getter and setter methods provided by the class to access or modify the value of this variable, as it is marked as private.
     * <p>
     * Example usage:
     * ```java
     * Trade trade = new Trade();
     * trade.setOriginalPRCTradeValuesImporterB("12345");
     * String originalPRCTradeValuesImporterB = trade.getOriginalPRCTradeValuesImporterB();
     * ```
     *
     * @see Trade
     * @see Trade#getOriginalPRCTradeValuesImporterB()
     * @see Trade#setOriginalPRCTradeValuesImporterB(String)
     */
    private String originalPRCTradeValuesImporterB;
    /**
     * Represents the value of originalLUXTradeValuesImporterA.
     * <p>
     * This variable is of type String and holds data related to the original trade values for importer A.
     * It is a private variable, meaning it can only be accessed and modified within the same class.
     * <p>
     * Example usage:
     * <pre>{@code
     * Trade trade = new Trade();
     * trade.setOriginalLUXTradeValuesImporterA("12345");
     * String originalLUXTradeValuesImporterA = trade.getOriginalLUXTradeValuesImporterA();
     * }</pre>
     *
     * @see Trade
     * @see Trade#getOriginalLUXTradeValuesImporterA()
     * @see Trade#setOriginalLUXTradeValuesImporterA(String)
     */
    private String originalLUXTradeValuesImporterA;
    /**
     * Represents a private variable in the Trade class.
     * <p>
     * This variable is of type String and holds the imported original trade values for Luxembourg (Importer B).
     * It is not accessible outside the class it belongs to.
     * <p>
     * It is recommended to use the getter and setter methods provided by the class to access or modify the value of this variable, as it is marked as private.
     * <p>
     * Example usage:
     * <pre>{@code
     * Trade trade = new Trade();
     * trade.setOriginalLUXTradeValuesImporterB("123456");
     * String originalLUXTradeValuesImporterB = trade.getOriginalLUXTradeValuesImporterB();
     * }</pre>
     *
     * @see Trade
     * @see Trade#getOriginalLUXTradeValuesImporterB()
     * @see Trade#setOriginalLUXTradeValuesImporterB(String)
     */
    private String originalLUXTradeValuesImporterB;
}
