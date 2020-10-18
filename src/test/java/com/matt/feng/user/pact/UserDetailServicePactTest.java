package com.matt.feng.user.pact;


import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@RunWith(PactRunner.class)
@Provider("user service")
@PactFolder("pacts")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port=8081"}
)
@Slf4j
public class UserDetailServicePactTest {

    private int port = 8081;
    @TestTarget
    public final Target target = new HttpTarget(port);

    @Before
    public void before() throws Exception {
        new TestContextManager(getClass()).prepareTestInstance(this);
    }

    @State("Have a valid userdetails")
    public void findAValidUserDetails() {
        log.debug("Pact test of having a valid userdetails.");
    }

    @State("No UserDetails were found")
    public void noUserDetailsFound() {
        log.debug("Pact test of no user details were found.");
    }

}
