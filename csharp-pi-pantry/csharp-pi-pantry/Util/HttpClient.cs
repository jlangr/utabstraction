namespace Pipantry.Util
{
    public class HttpClient
    {
        public string RetrieveText(string url)
        {
            return null;
            //CloseableHttpClient httpClient = HttpClients.createDefault();
            //try {
            //    return get(url, httpClient);
            //} catch (IOException e) {
            //    throw new RuntimeException(e);
            //} finally {
            //    try {
            //        httpClient.close();
            //    } catch (IOException e) {
            //        throw new RuntimeException(e);
            //    }
            //}
        }

        private string get(string url, /* CloseableHttpClient */ object httpClient)
        {
            //    HttpResponse response = httpClient.execute(new HttpGet(url));
            //return EntityUtils.toString(response.getEntity());
            return null;
        }
    }
}
