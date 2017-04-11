package com.langrsoft.testutil;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.css.CSSPrimitiveValue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class PresentationSlideExamples {
    private String description;
    private CSSPrimitiveValue text;

    /**
     * summary Javadoc/XMLDOC comment, redundant with test name
     */
    @Test
    public void someLongerTest() {
        // explain what example we're documenting next
        // ...

        // next case
    }

    @Test
    public void someHappyPathBehaviorWorks() {
        try {
            doSomethingThatShouldWorkUsing(someGoodData());

            assertThat(somethingWorkedAsExpected(), is(true));
        } catch (IOException willLikelyNeverHappen) {
            fail();
        }
    }

    private void doSomethingThatShouldWorkUsing(Object o) throws IOException {
    }

    class SomeObject {
    }

    @Ignore
    @Test
    public void someInterestingConstructionBehaviorWorks() {
        SomeObject obj = new SomeObject();
        assertThat(obj, is(notNullValue()));
        assertThat(someCharacteristicOf(obj), is(true));
    }

    class SomeResult {
    }

    @Ignore
    @Test
    public void someInterestingBehaviorWorks() {
        SomeResult obj = someInterestingBehavior();
        assertThat(obj, is(notNullValue()));
        assertThat(someCharacteristicOf(obj), is(true));
    }

    private SomeResult someInterestingBehavior() {
        return null;
    }

    private boolean someCharacteristicOf(Object obj) {
        return true;
    }

    private boolean somethingWorkedAsExpected() {
        return true;
    }

    private Object someGoodData() {
        return null;
    }

    Schema schema = new Schema();

    class CGSchema {
    }

    class Schema {
    }

    class Partition {
        private CGSchema[] CGSchemas = {new CGSchema()};

        public Partition(String s, String strStorage, Object o) throws Exception {
            if (!strStorage.equals(""))
                throw new RuntimeException("Encountered \" <IDENTIFIER> \"xyz \"\" at line 1, column 23.");
        }

        public CGSchema[] getCGSchemas() {
            return CGSchemas;
        }
    }

    @Test
    public void testStorageValid3() {
        try {
            String strStorage = "";
            Partition p = new Partition(schema.toString(), strStorage, null);
            CGSchema[] cgschemas = p.getCGSchemas();
            Assert.assertEquals(cgschemas.length, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void testStorageInvalid7() {
        try {
            String strStorage = "[f1, f2] serialize by xyz compress by gz; [f3, f4] SERIALIZE BY avro COMPRESS BY lzo";
            Partition p = new Partition(schema.toString(), strStorage, null);
            CGSchema[] cgschemas = p.getCGSchemas();
            CGSchema cgs1 = cgschemas[0];
            System.out.println(cgs1);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            String str = "Encountered \" <IDENTIFIER> \"xyz \"\" at line 1, column 23.";
            System.out.println(errMsg);
            System.out.println(str);
            Assert.assertEquals(errMsg.startsWith(str), true);
        }
    }


    class TestLogHandler {
        private Level level;

        public void setLevel(Level level) {
            this.level = level;
        }
    }

    static class Logger {
        public void addHandler(TestLogHandler handler) {
        }
    }

    interface DataReader {
        GCModel read();
    }

    class DataReaderJRockit1_6_0 implements DataReader {
        public DataReaderJRockit1_6_0(InputStream in) {
        }

        @Override
        public GCModel read() {
            return new GCModel();
        }
    }

    static Logger IMP_LOGGER = new Logger();
    static Logger DATA_READER_FACTORY_LOGGER = new Logger();

    class GCModel {
        public int size() {
            return 42;
        }

        public GCEvent get(int i) {
            return new GCEvent(24.930);
        }
    }

    class GCEvent {
        private double timestamp;

        public GCEvent(double timestamp) {
            this.timestamp = timestamp;
        }

        public double getTimestamp() {
            return timestamp;
        }
    }

    @Test
    public void testGcPrioPauseSingleParCon() throws Exception {
        TestLogHandler handler = new TestLogHandler();
        handler.setLevel(Level.WARNING);
        IMP_LOGGER.addHandler(handler);
        DATA_READER_FACTORY_LOGGER.addHandler(handler);

        InputStream in = getInputStream("SampleJRockit1_6_gc_mode_singleparcon.txt");
        DataReader reader = new DataReaderJRockit1_6_0(in);
        GCModel model = reader.read();

        assertEquals("count", 42, model.size());

        GCEvent event = (GCEvent) model.get(0);
        assertEquals("lastViewTimestamp", 24.930, event.getTimestamp(), 0.000001);
        // ...
    }

    private InputStream getInputStream(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }

    class KickstartWorkflow {
        private String name;
        private String description;

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void addTask(KickstartMailTask task) {
        }
    }

    class KickstartMailTask {
        private String id;
        private String name;
        private String description;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Container getTo() {
            return new Container();
        }

        public Container getSubject() {
            return new Container();
        }

        public Container getHtml() {
            return new Container();
        }

        public Container getText() {
            return new Container();
        }
    }

    class Container {

        private String expression;
        private String stringValue;

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public void setStringValue(String stringValue) {
            this.stringValue = stringValue;
        }
    }

    class MarshallingService {
        public String marshallWorkflow(KickstartWorkflow dto) {
            return "serviceTask :type=\"mail\"";
        }
    }

    MarshallingService marshallingService = new MarshallingService();

    @Test
    public void testEmailTaskWithSomeFieldsOnly() throws Exception {
        KickstartWorkflow dto = new KickstartWorkflow();
        dto.setName("One Mail Task Workflow");
        KickstartMailTask task = new KickstartMailTask();
        task.setId("myId");
        task.setName("My First Mail task");
        task.setDescription("Desc first Mail task");
        task.getTo().setStringValue("you@yourcompany.com");
        task.getSubject().setStringValue("my subject string");
        task.getHtml().setExpression("${my.html.expression}");
        task.getText().setStringValue("my text string");
        dto.addTask(task);

        String bpmn = marshallingService.marshallWorkflow(dto);

        assertTrue(bpmn.indexOf("serviceTask ") > -1);
        assertTrue(bpmn.indexOf(":type=\"mail\"") > -1);
        // ... nothing mentioning "one mail task"
    }

    class OftenContentDao {
        private List<OftenContent> data = new ArrayList<>();

        public void createOrUpdate(long contentId, String username, long timestamp, int score) {
            if (!contains(contentId, username))
                data.add(new OftenContent(contentId, username, timestamp, score));
            else
                update(contentId, username, timestamp, score);
        }

        private void update(long contentId, String username, long timestamp, int score) {
            OftenContent content = get(contentId, username);
            content.lastViewTimestamp = timestamp;
            content.score += score;
        }

        private OftenContent get(long contentId, String username) {
            return data.stream()
                    .filter(oc -> oc.contentId == contentId && oc.username.equals(username))
                    .findFirst().get();
        }

        private boolean contains(long contentId, String username) {
            return data.stream().anyMatch(oc -> oc.contentId == contentId && oc.username.equals(username));
        }

        public List<OftenContent> findOftenContent(String username) {
            return data.stream().filter(oc -> oc.username.equals(username))
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        }
    }

    OftenContentDao dao = new OftenContentDao();

    class ActiveObject {
        public void flush() {
        }
    }

    ActiveObject ao = new ActiveObject();

    class OftenContent implements Comparable<OftenContent> {
        private int score;
        private long contentId;
        private String username;
        private long lastViewTimestamp;

        public OftenContent(long contentId, String username, long timestamp, int score) {
            this.contentId = contentId;
            this.username = username;
            this.lastViewTimestamp = timestamp;
            this.score = score;
        }

        public int getScore() {
            return score;
        }

        public long getContentId() {
            return contentId;
        }

        @Override
        public int compareTo(OftenContent that) {
            if (this.score < that.score) return -1;
            if (this.score > that.score) return 1;
            return 0;
        }
    }

    @Test
    public void createOrUpdateShouldUpdateScore() throws Exception {
        dao.createOrUpdate(0, "test", 0, 1);
        dao.createOrUpdate(0, "test", 1, 2);
        ao.flush();

        List<OftenContent> oftenContents = dao.findOftenContent("test");
        assertThat(oftenContents.size(), is(1));
        assertThat(oftenContents.get(0).getScore(), is(3));
    }

    @Test
    public void findOftenContentSortByDescendingScore() throws Exception {
        dao.createOrUpdate(1, "test", 0, 1);
        dao.createOrUpdate(2, "test", 2, 2);
        dao.createOrUpdate(3, "test", 3, 3);
        ao.flush();

        List<OftenContent> oftenContents = dao.findOftenContent("test");

        assertThat(oftenContents.size(), is(3));
        assertThat(oftenContents.get(0).getContentId(), is(3L));
        assertThat(oftenContents.get(1).getContentId(), is(2L));
        assertThat(oftenContents.get(2).getContentId(), is(1L));
    }

    static class Repository {}
    static class SesameTripleRepositoryFactory {
        public static Repository createInMemoryRepository() {
            return new Repository();
        }
    }
    static class SesameConceptRepositoryFactory {
        public static ObjectRepository createConceptRepository(Repository repository, Set<Class<?>> concepts, String s) {
            return new ObjectRepository();
        }
    }
    static class ObjectRepository {}
    class User {}
    class Project {}
    class SesameConceptRepository {
        public SesameConceptRepository(ObjectRepository objectRepository) {
        }
    }

    @Test
	public void testGetURI() {
        Repository repository = SesameTripleRepositoryFactory.createInMemoryRepository();
        Assert.assertNotNull(repository);
        Set<Class<?>> concepts = new HashSet<Class<?>>();
        Assert.assertNotNull(concepts);
        concepts.add(User.class);
        concepts.add(Project.class);
        ObjectRepository objectRepository = SesameConceptRepositoryFactory
                .createConceptRepository(repository, concepts, "http://...");
        Assert.assertNotNull(objectRepository);
        SesameConceptRepository conceptRepository = new SesameConceptRepository(
                objectRepository);
        Assert.assertNotNull(conceptRepository);
        // ...
	}
}
