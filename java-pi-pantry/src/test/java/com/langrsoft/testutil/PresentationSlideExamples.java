package com.langrsoft.testutil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.css.CSSPrimitiveValue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class PresentationSlideExamples {
    private String description;
    private CSSPrimitiveValue text;
    private StopPlaats stopPlaats;

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

    static class Repository {
    }

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

    static class ObjectRepository {
    }

    class User {
    }

    class Project {
    }

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

    class TestIntRangeManager {
        public List<Integer> mConfigList = new ArrayList<>();
//        {
//            mConfigList.add(1);
//        }
    }

    @Test
    public void emptyRangeManager() {
        TestIntRangeManager testManager = new TestIntRangeManager();
        assertEquals("expecting empty configlist", 0, testManager.mConfigList.size());
    }

    class HttpServletRequestImpl {
        private Map<String, String> params = new HashMap<>();

        public String getParameter(String key) {
            return params.get(key);
        }

        public void addParams(String paramsString) {
            for (String pair : paramsString.split("&")) {
                String[] keyValue = pair.split("=");
                String key = keyValue[0];
                if (!params.containsKey(key)) {
                    String value = decode(keyValue[1]);
                    params.put(key, value);
                }
            }
        }

        private String decode(String s) {
            return s.replace("%20", " ");
        }

        public void post(String url, String body) {
            String postPath = url.split(" ")[1];
            String postPathParams = postPath.split("\\?")[1];
            addParams(postPathParams);
            addParams(body);
        }

        public void execute(String content) {
            String[] stuff = content.split("\r\n");
            String body = stuff[stuff.length - 1];
            String postUrl = stuff[0];
            // assume only posts for now
            post(postUrl, body);
        }
    }

    private HttpServletRequestImpl request = new HttpServletRequestImpl();

    private void addRawRequestContent(String post) {
        request.execute(post);
    }

    @Before
    public void setup() {
        addRawRequestContent(
                "POST /context/path1/path2/index.html?x=y&g=2%20g HTTP/1.1 \r\n" +
                "Date: Tue, 01 May 2012 06:46:45 GMT \r\n" +
                "Connection: close \r\n" +
                "Host: www.myfavouriteamazingsite.com\r\n" +
                "From: joebloe@somewebsitesomewhere.com \r\n" +
                "Accept: text/html, text/plain \r\n" +
                "Cookie: $Version=\"1\"; Customer=\"WILE_E_COYOTE\"; $Path=\"/acme\"; \r\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1) \r\n" +
                "Accept-Language: en-US, jp \r\n" +
                "Accept-Language: cn \r\n" +
                "Content-Length: " +
                ("param1=value1&param2=value2&param1=value2").getBytes().length + "\r\n" +
                "Content-Type: application/x-www-form-urlencoded;charset=UTF-8\r\n" +
                "\r\n" +
                "param1=value1&param2=value2&param1=value2"
        );
        // ...
    }

    @Test
    public void test_getParameter() throws Exception {
        // param1=value1&param2=value2&param1=value2
        assertEquals(request.getParameter("x"), "y");
        assertEquals(request.getParameter("g"), "2 g");
        assertEquals(request.getParameter("param1"), "value1");
        assertEquals(request.getParameter("param2"), "value2");
    }

    static class EncounterService {
        public Encounter getEncounter(int i) {
            return new Encounter();
        }

        public void saveEncounter(Encounter encounter) {
            encounter.getObs().setObsId("1");
        }
    }

    static class Context {
        private static EncounterService encounterService;

        public static EncounterService getEncounterService() {
            return new EncounterService();
        }
    }

    static class Encounter {
        private LocalDateTime encounterDatetime = LocalDateTime.now();
        private Obs obs;

        public LocalDateTime getEncounterDatetime() {
            return encounterDatetime;
        }

        public void addObs(Obs obs) {
            this.obs = obs;
        }

        public Obs getObs() {
            return obs;
        }
    }

    class Obs {
        private double valueNumeric;
        private Date obsDatetime;
        private String obsId;

        public void setValueNumeric(double valueNumeric) {
            this.valueNumeric = valueNumeric;
        }

        public void setObsDatetime(Date obsDatetime) {
            this.obsDatetime = obsDatetime;
        }

        public String getObsId() {
            return obsId;
        }

        public void setObsId(String obsId) {
            this.obsId = obsId;
        }
    }


    /**
     * You should be able to add an obs to an encounter, save the encounter, and have the obs
     * automatically persisted.
     */
    @Test
    public void saveEncounter_shouldCascadeSaveToContainedObsWhenEncounterAlreadyExists() {
        EncounterService es = Context.getEncounterService();

        // get an encounter from the database
        Encounter encounter = es.getEncounter(1);
        assertNotNull(encounter.getEncounterDatetime());

        // Now add an obs to it
        Obs obs = new Obs();
        obs.setValueNumeric(50d);
        obs.setObsDatetime(new Date());
        encounter.addObs(obs);

        // there should not be an obs id before saving the encounter
        assertNull(obs.getObsId());

        es.saveEncounter(encounter);

        // the obs id should have been populated during the save
        assertNotNull(obs.getObsId());
    }

    @Test
    public void testNonCaptConstr() {
        // Flags
        Pattern pat = Pattern.compile("(?i)b*(?-i)a*");
        assertTrue(pat.matcher("bBbBaaaa").matches());
        assertFalse(pat.matcher("bBbBAaAa").matches());

        // Non-capturing groups
        pat = Pattern.compile("(?i:b*)a*");
        assertTrue(pat.matcher("bBbBaaaa").matches());
        assertFalse(pat.matcher("bBbBAaAa").matches());

        // positive lookahead
        pat = Pattern.compile(".*\\.(?=log$).*$");
        assertTrue(pat.matcher("a.b.c.log").matches());
        assertFalse(pat.matcher("a.b.c.log.").matches());
    }

    @Test
    public void test_Reader_CharBuffer_ZeroChar() throws IOException {
        //the charBuffer has the capacity of 0, then there the number of char read
        // to the CharBuffer is 0. Furthermore, the MockReader is intact in its content.
        String s = "MY TEST STRING";
        char[] srcBuffer = s.toCharArray();
        MockReader mockReader = new MockReader(srcBuffer);
        CharBuffer charBuffer = CharBuffer.allocate(0);
        int result = mockReader.read(charBuffer);
        assertEquals(0, result);
        char[] destBuffer = new char[srcBuffer.length];
        mockReader.read(destBuffer);
        assertEquals(s, String.valueOf(destBuffer));
    }

    class MockReader extends Reader {
        private char[] contents;
        private int current_offset = 0;
        private int length = 0;

        public MockReader() {
            super();
        }

        public MockReader(char[] data) {
            contents = data;
            length = contents.length;
        }

        @Override
        public void close() throws IOException {
            contents = null;
        }

        @Override
        public int read(char[] buf, int offset, int count) throws IOException {
            if (null == contents) {
                return -1;
            }
            if (length <= current_offset) {
                return -1;
            }
            if (buf.length < offset + count) {
                throw new IndexOutOfBoundsException();
            }
            count = Math.min(count, length - current_offset);
            for (int i = 0; i < count; i++) {
                buf[offset + i] = contents[current_offset + i];
            }
            current_offset += count;
            return count;
        }

    }

    class CentroidProcess {
        public SimpleFeatureCollection execute(ListFeatureCollection fc) {
            SimpleFeatureCollection c = new SimpleFeatureCollection();
            c.add(new SimpleFeature(0, 0, "one"));
            c.add(new SimpleFeature(10, 0, "two"));
            return c;
        }
    }

    class Point {
        private double x;
        private double y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

    class SimpleFeatureCollection {
        List<SimpleFeature> features = new ArrayList<>();

        public SimpleFeatureIterator features() {
            return new SimpleFeatureIterator(features);
        }

        public void add(SimpleFeature simpleFeature) {
            features.add(simpleFeature);
        }
    }

    class SimpleFeatureIterator implements Iterator<SimpleFeature> {
        private Iterator<SimpleFeature> featuresIterator;
        private List<SimpleFeature> features;

        public SimpleFeatureIterator(List<SimpleFeature> features) {
            this.features = features;
            featuresIterator = features.iterator();
        }

        @Override
        public boolean hasNext() {
            return featuresIterator.hasNext();
        }

        @Override
        public SimpleFeature next() {
            return featuresIterator.next();
        }
    }

    class SimpleFeature {
        private Object defaultGeometry;
        private String name;

        public SimpleFeature(int x, int y, String name) {
            this.name = name;
            this.defaultGeometry = new Point(x, y);
        }

        public Object getDefaultGeometry() {
            return defaultGeometry;
        }

        public String getAttribute(String key) {
            return name;
        }

        @Override
        public String toString() {
            return "(" + ((Point) defaultGeometry).getX() + "," + ((Point) defaultGeometry).getY() + ")" + name;
        }
    }

    class ListFeatureCollection {
    }

    ListFeatureCollection fc = new ListFeatureCollection();

    @Test
    public void testResults() throws Exception {
        CentroidProcess cp = new CentroidProcess();
        SimpleFeatureCollection result = cp.execute(fc);

        SimpleFeatureIterator it = result.features();
        assertTrue(it.hasNext());
        SimpleFeature f = it.next();
        assertEquals(0, ((Point) f.getDefaultGeometry()).getX(), 1e-6);
        assertEquals(0, ((Point) f.getDefaultGeometry()).getY(), 1e-6);
        assertEquals("one", f.getAttribute("name"));
        f = it.next();
        assertEquals(10, ((Point) f.getDefaultGeometry()).getX(), 1e-6);
        assertEquals(0, ((Point) f.getDefaultGeometry()).getY(), 1e-6);
        assertEquals("two", f.getAttribute("name"));
    }

    class StopPlaatsController {
        public String addStopPlaats(StopPlaats sp, Object o, int tripId) {
            return "";
        }

        public ModelAndView updateStopPlaatsPage(MockHttpServletRequest mockHttpServletRequest, Object o, String stopPlaatsID) {
            return new ModelAndView();
        }
    }

    StopPlaatsController stopPlaatsController = new StopPlaatsController();

    class ModelAndView {
        public String getViewName() {
            return "StopPlaats/updateStopPlaats";
        }
    }

    class Trip {
        private ArrayList<StopPlaats> stopPlaatsen;
        private int tripId;

        public void setStopPlaatsen(ArrayList<StopPlaats> stopPlaatsen) {
            this.stopPlaatsen = stopPlaatsen;
        }

        public ArrayList<StopPlaats> getStopPlaatsen() {
            return stopPlaatsen;
        }

        public int getTripId() {
            return tripId;
        }
    }

    class TripDAO {
        public void addTrip(Trip t) {
        }
    }

    class StopPlaats {
        private String stopPlaatsID;

        public String getStopPlaatsID() {
            return stopPlaatsID;
        }
    }

    class MockHttpServletRequest {

    }

    TripDAO tripDAO = new TripDAO();

    @Test
    public void testUpdateStopPlaatsPage() {
        ModelAndView mav = null;
        Trip t = new Trip();
        t.setStopPlaatsen(new ArrayList<StopPlaats>());
        StopPlaats sp = getStopPlaats();
        tripDAO.addTrip(t);
        t.getStopPlaatsen().add(sp);

        String s = "";
        s = stopPlaatsController.addStopPlaats(sp, null, t.getTripId());

        try {
            mav = stopPlaatsController.updateStopPlaatsPage(
                    new MockHttpServletRequest(), null, sp.getStopPlaatsID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("StopPlaats/updateStopPlaats", mav.getViewName());
    }

    public StopPlaats getStopPlaats() {
        return new StopPlaats();
    }
}
