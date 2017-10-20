package com.mattmcnew.privatessn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.paulcwarren.ginkgo4j.Ginkgo4jRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import static com.github.paulcwarren.ginkgo4j.Ginkgo4jDSL.*;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(Ginkgo4jRunner.class)
public class PrivateSSNTest {
    {
        Describe("PrivateSSN", () -> {
            Context("Keeping SSN Private", () -> {
                It("does not expose via toString ", () -> {
                    PrivateSSN privateSSN = new PrivateSSN("1234");

                    assertThat(privateSSN.toString()).doesNotContain("1");
                    assertThat(privateSSN.toString()).doesNotContain("2");
                    assertThat(privateSSN.toString()).doesNotContain("3");
                    assertThat(privateSSN.toString()).doesNotContain("4");
                });

                It("does not expose SSN from any public method", () -> {
                    PrivateSSN privateSSN = new PrivateSSN("1234");

                    Stream.of(PrivateSSN.class.getMethods()).filter((method) -> method.getParameterCount() == 0)
                            .forEach((method) -> {
                                        Object invoke;
                                        try {
                                            invoke = method.invoke(privateSSN);
                                        } catch (IllegalAccessException | InvocationTargetException e) {
                                            return;
                                        }

                                        assertThat(invoke.toString()).doesNotContain("1234");
                                    }
                            );
                });

                It("does not expose SSN as an accessible field ", () -> {
                    PrivateSSN privateSSN = new PrivateSSN("1234");

                    Field[] fields = PrivateSSN.class.getFields();

                    for (Field field : fields) {
                        assertThat(field.get(privateSSN).toString()).isNotEqualTo("1234");
                    }
                });
            });

            Context("Retrieving SSN", () -> {
                It("It is accessible with Extracting Class", () -> {
                    PrivateSSN privateSSN = new PrivateSSN("1234");

                    NoReallyIWantToReadThePrivateSSN noReallyIWantToReadThePrivateSSN = new NoReallyIWantToReadThePrivateSSN();

                    String ssn = noReallyIWantToReadThePrivateSSN.iActuallyWantToReadSSN(privateSSN);

                    assertThat(ssn).isEqualTo("1234");
                });
            });


            Context("Private SSN Equality", () -> {
                It("It is accessible with Extracting Class", () -> {
                    PrivateSSN privateSSN = new PrivateSSN("1234");

                    assertThat(privateSSN).isEqualTo(new PrivateSSN("1234"));
                    assertThat(privateSSN).isNotEqualTo(new PrivateSSN("1"));
                    assertThat(privateSSN).isNotEqualTo(new PrivateSSN("2"));
                    assertThat(privateSSN).isNotEqualTo(new PrivateSSN("12345"));
                });
            });


            Context("DE/Serializing to JSON", () -> {
                ObjectMapper objectMapper = new ObjectMapper();
                SimpleModule module = new SimpleModule();
                module.addSerializer(PrivateSSN.class, new PrivateSSNSerializer());
                objectMapper.registerModule(module);


                It("can be deserialized from JSON", () -> {
                    SomeJSONObject someJSONObject = objectMapper.readValue("{\"socialSecurityNum\": \"789-567\"}", SomeJSONObject.class);

                    assertThat(someJSONObject.getSocialSecurityNum()).isEqualTo(new PrivateSSN("789-567"));
                });

                It("can be serialized from JSON", () -> {
                    String json = objectMapper.writeValueAsString(new SomeJSONObject(new PrivateSSN("789-567")));

                    assertThat(json).isEqualTo("{\"socialSecurityNum\":\"789-567\"}");
                });
            });
        });
    }
}
