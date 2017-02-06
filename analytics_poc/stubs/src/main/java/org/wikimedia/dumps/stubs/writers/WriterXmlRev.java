package org.wikimedia.dumps.stubs.writers;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.CharacterCodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javanet.staxutils.IndentingXMLStreamWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.wikimedia.dumps.stubs.models.Page;
import org.wikimedia.dumps.stubs.models.Comment;
import org.wikimedia.dumps.stubs.models.Revision;
import org.wikimedia.dumps.stubs.models.Contributor;
import org.wikimedia.dumps.stubs.models.Text;
import org.wikimedia.dumps.stubs.models.Empty;
import org.wikimedia.dumps.stubs.readers.GetterRev;
import org.wikimedia.dumps.stubs.readers.GetterPage;


public class WriterXmlRev {
    public static void writePage(GetterPage getterPage, ResultSet pageResults,
                List<XMLStreamWriter> writers, Marshaller jaxbMarshallerRev)
                throws SQLException, XMLStreamException, FileNotFoundException,
                IOException, CharacterCodingException, ClassNotFoundException {
        Page page = new Page();
        String field = getterPage.cleanupField("PAGE_ID", pageResults);
        page.setId(Integer.parseInt(field));
        field = getterPage.cleanupField("PAGE_TITLE", pageResults);
        page.setTitle(field);
        field = getterPage.cleanupField("PAGE_NAMESPACE", pageResults);
        page.setNs(Integer.parseInt(field));

        writePagePartial(page, writers);

        GetterRev getterRev = new GetterRev();
        ResultSet revResults = getterRev.getRevisionsForPage(page.getId());
        while (revResults.next()) {
            // rev contents
            writeRevision(getterRev, revResults, jaxbMarshallerRev, writers);
        }
        // end page
        for (XMLStreamWriter writer: writers) {
            writer.writeEndElement();
        }
    }

    public static void writePagePartial(Page page, List<XMLStreamWriter> writers)
                throws XMLStreamException {
        for (XMLStreamWriter writer: writers) {
            // start page
            writer.writeStartElement("page");
            // page contents except for revs
            writer.writeStartElement("title");
            writer.writeCharacters(page.getTitle());
            writer.writeEndElement();
            writer.writeStartElement("ns");
            writer.writeCharacters(Integer.toString(page.getNs()));
            writer.writeEndElement();
            writer.writeStartElement("id");
            writer.writeCharacters(Integer.toString(page.getId()));
            writer.writeEndElement();
            writer.flush();
        }
    }

    public static void writeRevision(GetterRev getterRev, ResultSet revResults,
                Marshaller jaxbMarshaller, List<XMLStreamWriter> writers)
                throws SQLException, CharacterCodingException {
        Revision rev = new Revision();
        String field = getterRev.cleanupField("REV_DELETED", revResults);
        Integer deleted = Integer.parseInt(field);

        field = getterRev.cleanupField("REV_ID", revResults);
        rev.setId(Integer.parseInt(field));
        Comment comment = new Comment();
        if ((deleted & 2) != 0) {
            // empty tag with 'deleted' attr here
            comment.setDeleted("deleted");
            comment.setComment(null);
        } else {
            field = getterRev.cleanupField("REV_COMMENT", revResults);
            comment.setComment(field);
        }
        Text text = new Text();
        if ((deleted & 1) != 0) {
            // empty tag with 'deleted' attr here
            text.setDeleted("deleted");
        } else {
            field = getterRev.cleanupField("REV_TEXT_ID", revResults);
            text.setId(Integer.parseInt(field));
            field = getterRev.cleanupField("REV_LEN", revResults);
            text.setBytes(Integer.parseInt(field));
        }
        Contributor contr = new Contributor();
        if ((deleted & 4) != 0) {
            // empty tag with 'deleted' attr here
            contr.setDeleted("deleted");
        } else {
            field = getterRev.cleanupField("REV_USER", revResults);
            contr.setId(Integer.parseInt(field));
            field = getterRev.cleanupField("REV_USER_TEXT", revResults);
            contr.setUsername(field);
        }
        field = getterRev.cleanupField("REV_TIMESTAMP", revResults);
        rev.setTimestamp(field);
        field = getterRev.cleanupField("REV_MINOR_EDIT", revResults);
        Integer isMinor = Integer.parseInt(field);
        if (isMinor > 0) {
            rev.setMinor(new Empty());
        }
        if ((deleted & 1) != 0) {
            // FIXMEEEEEE
            // empty tag here
        } else {
            field = getterRev.cleanupField("REV_SHA1", revResults);
            rev.setSha1(field);
        }
        try {
            field = getterRev.cleanupField("REV_CONTENT_MODEL", revResults);
            rev.setModel(field);
        } catch (NullPointerException e) {
            // what is the null placeholder here?
            rev.setModel("some_default_FIXME");
        }
        try {
            field = getterRev.cleanupField("REV_CONTENT_FORMAT", revResults);
            rev.setFormat(field);
        } catch (NullPointerException e) {
            // what is the null placeholder here?
            rev.setFormat("some_default_FIXME");
        }
        rev.setComment(comment);
        rev.setContributor(contr);
        rev.setText(text);
        for (XMLStreamWriter writer: writers) {
            try {
                jaxbMarshaller.marshal(rev, writer);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }
}
    
