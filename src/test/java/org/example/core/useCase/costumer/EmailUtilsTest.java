package org.example.core.useCase.costumer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailUtilsTest {
    @Test
    public void testObscureEmailWithNull() {
        assertNull(EmailUtils.obscureEmail(null));
    }

    @Test
    public void testObscureEmailWithInvalidFormat() {
        String email = "emailsemformato";
        assertEquals(email, EmailUtils.obscureEmail(email));
    }

    @Test
    public void testObscureEmailWithShortLocalPart() {
        String email = "e@domain.com";
        assertEquals(email, EmailUtils.obscureEmail(email));
    }

    @Test
    public void testObscureEmailWithValidFormat() {
        String email = "example@email.com";
        String expectedObscuredEmail = "e******@email.com";
        assertEquals(expectedObscuredEmail, EmailUtils.obscureEmail(email));
    }

    @Test
    public void testObscureLocalPart() {
        // Teste com obscureLocalPart quando o localPart tem 5 caracteres.
        String localPart = "local";
        String expectedObscuredLocalPart = "l****";
        assertEquals(expectedObscuredLocalPart, EmailUtils.obscureLocalPart(localPart));
    }

    @Test
    public void testRepeatCharacter() {
        // Teste para o método repeatCharacter com count igual a 0.
        assertEquals("", EmailUtils.repeatCharacter(0));

        // Teste para o método repeatCharacter com count igual a 3.
        assertEquals("***", EmailUtils.repeatCharacter(3));
    }
}