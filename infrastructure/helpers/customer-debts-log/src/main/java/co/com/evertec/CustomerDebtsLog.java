package co.com.evertec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

public class CustomerDebtsLog implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(CustomerDebtsLog.class);

    public static void logErrorMessage(final Class<?> thisClass, final String srcErrorMessage) {
        logErrorMessage(thisClass, srcErrorMessage, null);
    }

    /**
     * Log Error
     *
     * @param thisClass
     * @param srcErrorMessage
     * @param srcException
     */
    public static void logErrorMessage(final Class<?> thisClass, final String srcErrorMessage,
                                       final Throwable srcException) {
        String errorMessage = "[" + thisClass.getCanonicalName() + "]";

        if (srcErrorMessage != null) {
            errorMessage = errorMessage + " " + srcErrorMessage;
        }

        Exception exception;
        if (srcException == null) {
            logger.error(errorMessage);
        } else {
            exception = new Exception(errorMessage, srcException);
            logger.error(errorMessage, exception);
        }
    }

    /**
     * Metodo que permite ingresar un mensaje informativo al log.
     *
     * @param thisClass   Clase en la que se genera el error.
     * @param infoMessage Mensaje.
     */
    public static void logInfoMessage(final Class<?> thisClass, final String infoMessage) {
        String message = "[" + thisClass.getCanonicalName() + "]";

        if (infoMessage != null) {
            message = message + " " + infoMessage;
        }

        logger.info(message);
    }
}