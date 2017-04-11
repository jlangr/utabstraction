package com.langrsoft.testutil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class PresentationSlideExamplesCleaned {

    @Test
    public void someHappyPathBehaviorWorks() throws IOException {
        doSomethingThatShouldWorkUsing(someGoodData());

        assertThat(somethingWorkedAsExpected(), is(true));
    }

    private void doSomethingThatShouldWorkUsing(Object o) throws IOException {
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
    public void testStorageValid3() throws Exception {
        String strStorage = "";
        Partition p = new Partition(schema.toString(), strStorage, null);
        CGSchema[] cgschemas = p.getCGSchemas();
        Assert.assertEquals(cgschemas.length, 1);
    }

    @Test
    public void testStorageInvalid7() {
        try {
            String strStorage = "[f1, f2] serialize by xyz compress by gz; [f3, f4] SERIALIZE BY avro COMPRESS BY lzo";
            Partition p = new Partition(schema.toString(), strStorage, null);
            p.getCGSchemas();
        } catch (Exception e) {
            String errMsg = e.getMessage();
            String str = "Encountered \" <IDENTIFIER> \"xyz \"\" at line 1, column 23.";
            assertEquals(errMsg.startsWith(str), true);
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

    @Before
    public void suppressLoggingBelowWarning() {
        TestLogHandler handler = new TestLogHandler();
        handler.setLevel(Level.WARNING);
        IMP_LOGGER.addHandler(handler);
        DATA_READER_FACTORY_LOGGER.addHandler(handler);
    }

    @Test
    public void testGcPrioPauseSingleParCon() throws Exception {
        InputStream in = getInputStream("SampleJRockit1_6_gc_mode_singleparcon.txt");
        DataReader reader = new DataReaderJRockit1_6_0(in);
        GCModel model = reader.read();

        assertEquals("count", 42, model.size());

        GCEvent event = (GCEvent) model.get(0);
        assertEquals("timestamp", 24.930, event.getTimestamp(), 0.000001);
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

    private KickstartWorkflow dto = new KickstartWorkflow();
    private KickstartMailTask task;

    @Test
    public void testEmailTaskWithSomeFieldsOnly() throws Exception {
        task = new KickstartMailTask();
        task.setId("myId");
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

    class TaskBuilder {
        private String name = "some default value";
        private String id = "someDefaultId";
        private String to = "x@example.com";
        private String subject = "my subject string";
        private String text = "my text string";
        private String html = "<html/>";

        public TaskBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public TaskBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public TaskBuilder withTo(String to) {
            this.to = to;
            return this;
        }

        public TaskBuilder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public TaskBuilder withHtml(String html) {
            this.html = html;
            return this;
        }

        public TaskBuilder withText(String text) {
            this.text = text;
            return this;
        }

        KickstartMailTask create() {
            KickstartMailTask task = new KickstartMailTask();
            task.setName(name);
            task.getTo().setStringValue(to);
            task.getSubject().setStringValue(subject);
            task.getHtml().setExpression(html);
            task.getText().setStringValue(text);
            return task;
        }
    }

    @Test
    public void populatesTemplateWithEmailAddress() throws Exception {
        task = new TaskBuilder().withHtml("${my.html.expression}").create();
        dto.addTask(task);

        String bpmn = marshallingService.marshallWorkflow(dto);

        assertTrue(containsExpression(bpmn, "${my.html.expression}"));
    }

    private boolean containsExpression(String bpmn, String s) {
        return true;
    }

    class OftenContentDao {
        private List<OftenContent> data = new ArrayList<>();

        public void createOrUpdate(long contentId, String username, long timestamp, long score) {
            if (!contains(contentId, username))
                data.add(new OftenContent(contentId, username, timestamp, score));
            else
                update(contentId, username, timestamp, score);
        }

        private void update(long contentId, String username, long timestamp, long score) {
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
        private long score;
        private long contentId;
        private String username;
        private long lastViewTimestamp;

        public OftenContent(long contentId, String username, long timestamp, long score) {
            this.contentId = contentId;
            this.username = username;
            this.lastViewTimestamp = timestamp;
            this.score = score;
        }

        public long getScore() {
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

    static final int SOME_CONTENT_ID = 0;
    int startOfTimeInMS = 0;

    @Test
    public void createOrUpdateShouldUpdateScore() throws Exception {
        dao.createOrUpdate(SOME_CONTENT_ID, "test", startOfTimeInMS, 10);
        dao.createOrUpdate(SOME_CONTENT_ID, "test", ++startOfTimeInMS, 20);
        ao.flush();

        List<OftenContent> oftenContents = dao.findOftenContent("test");
        assertThat(oftenContents.size(), is(1));
        assertThat(oftenContents.get(0).getScore(), is(30L));
    }

    @Test
    public void createOrUpdateShouldUpdateScore2() throws Exception {
        dao.createOrUpdate(SOME_CONTENT_ID, "test", startOfTimeInMS, 10);
        dao.createOrUpdate(SOME_CONTENT_ID, "test", ++startOfTimeInMS, 20);
        ao.flush();

        List<OftenContent> oftenContents = dao.findOftenContent("test");
        assertThat(oftenContents.size(), is(1));
        assertThat(oftenContents.get(0).getScore(), is(30L));
    }

    @Test
    public void findOftenContentSortByDescendingScore() throws Exception {
        dao.createOrUpdate(SOME_CONTENT_ID, "test", startOfTimeInMS, 1);
        dao.createOrUpdate(SOME_CONTENT_ID + 1, "test", ++startOfTimeInMS, 2);
        dao.createOrUpdate(SOME_CONTENT_ID + 2, "test", ++startOfTimeInMS, 3);
        ao.flush();

        List<OftenContent> results = dao.findOftenContent("test");

        assertThat(scores(results), is(equalTo(asList(3L, 2L, 1L))));
    }

    private List<Long> scores(List<OftenContent> results) {
        return results.stream().map(OftenContent::getScore).collect(toList());
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
        concepts.add(User.class);
        concepts.add(Project.class);
        ObjectRepository objectRepository = SesameConceptRepositoryFactory
                .createConceptRepository(repository, concepts, "http://...");
        Assert.assertNotNull(objectRepository);
        SesameConceptRepository conceptRepository = new SesameConceptRepository(
                objectRepository);
        // ...
    }

    @Test
    public void testGetUri() {
        Repository repository = SesameTripleRepositoryFactory.createInMemoryRepository();
        Set<Class<?>> concepts = new HashSet<Class<?>>();
        concepts.add(User.class);
        concepts.add(Project.class);
        ObjectRepository objectRepository = SesameConceptRepositoryFactory
                .createConceptRepository(repository, concepts, "http://...");
        SesameConceptRepository conceptRepository = new SesameConceptRepository(
                objectRepository);
        // ...
    }
}
