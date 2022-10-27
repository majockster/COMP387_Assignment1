package assignment.team._387a2.helperObjects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CookieHelper {
    public static Cookie GetCookieByName(HttpServletRequest pRequest, String pName)
    {
        // Get all cookies
        Cookie[] cookies = pRequest.getCookies();
        // Check if there are any cookies.
        if (cookies != null)
        {
            // Loop over all cookies
            for (Cookie individualCookie : cookies)
            {
                // Check if each cookie has the name we are looking for.
                if (individualCookie.getName().equals(pName))
                {
                    return individualCookie;
                }
            }
        }
        return null;
    }

    public static Map<String, Cookie> ConvertRequestCookies(HttpServletRequest pRequest)
    {
        Map<String, Cookie> cookiesMap = new HashMap<>();
        // Insert each cookie in the map, with its name as a key.
        for (Cookie cookie : pRequest.getCookies())
        {
            cookiesMap.put(cookie.getName(), cookie);
        }

        return cookiesMap;
    }
}
