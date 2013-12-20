import static ratpack.groovy.Groovy.*

import org.apache.commons.io.FileUtils;

ratpack {
    handlers {
        handler {
            def uri = request.uri
            File cache = new File("cache")
            File cachedFile = new File(cache, uri)
            if(!cachedFile.exists()) {
                URL pearURL = new URL("http://pear.php.net$uri")
                InputStream is = pearURL.openStream()
                try {
                    FileUtils.copyInputStreamToFile(is, cachedFile)
                } finally {
                    is.close()
                }
            }
            response.send(new FileInputStream(cachedFile))
        }    
    }
}
