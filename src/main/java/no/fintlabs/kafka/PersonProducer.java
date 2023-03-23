package no.fintlabs.kafka;


import no.fintlabs.kafka.entity.EntityProducer;
import no.fintlabs.kafka.entity.EntityProducerRecord;
import no.fintlabs.kafka.entity.topic.EntityTopicNameParameters;
import no.fintlabs.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonProducer {

    private final EntityProducer<Person> entityProducer;

    public PersonProducer(EntityProducer<Person> entityProducer) {
        this.entityProducer = entityProducer;
    }

    public void send(Person person) {
        // Lag en EntityTopicNameParameters
        EntityTopicNameParameters entityTopicNameParameters = EntityTopicNameParameters
                .builder()
                .orgId("fintlabs-no")
                .domainContext("fint-core")
                .resource("person")
                .build();

        // Lag en EntityProducerRecord av Person
        EntityProducerRecord<Person> entityProducerRecord = EntityProducerRecord
                .<Person>builder()
                .topicNameParameters(entityTopicNameParameters)
                .value(person)
                .build();

        // Send entityProducerRecord
        entityProducer.send(entityProducerRecord);
    }

}
