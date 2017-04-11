using System;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;

namespace Pipantry.Util
{
    public class Http
    {
        virtual public string RetrieveText(string url)
        {
            return RetrieveText(url,
                status => { throw new ApplicationException($"http get failed: {status}"); });
        }

        virtual public string RetrieveText(string url, Func<HttpStatusCode, string> errorHandler)
        {
            HttpResponseMessage response = null;
            var client = new HttpClient();
            client.BaseAddress = new Uri(url);
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
            response = client.GetAsync(url).Result;
            if (response.IsSuccessStatusCode)
                return response.Content.ReadAsStringAsync().Result;
            return errorHandler.Invoke(response.StatusCode);
        }
    }
}
