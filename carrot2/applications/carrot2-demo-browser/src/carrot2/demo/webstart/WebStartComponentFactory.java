package carrot2.demo.webstart;

import java.util.HashMap;

import carrot2.demo.cache.RawDocumentProducerCacheWrapper;

import com.dawidweiss.carrot.core.local.LocalComponent;
import com.dawidweiss.carrot.core.local.LocalComponentFactoryBase;
import com.dawidweiss.carrot.core.local.impl.RawDocumentEnumerator;
import com.dawidweiss.carrot.core.local.linguistic.Language;
import com.dawidweiss.carrot.filter.stc.local.STCLocalFilterComponent;
import com.dawidweiss.carrot.util.tokenizer.languages.english.English;
import com.stachoodev.carrot.filter.lingo.local.LingoLocalFilterComponent;

/**
 * A brute-force workaround for problems with security exceptions when Beanshell
 * attempts to create anonymous classes in a (signed!) WebStart application: we
 * will create components using a public factory-like class.
 * 
 * @author Dawid Weiss
 */
public class WebStartComponentFactory {

    /**
     * <code>input-cached-yahooapi</code>
     */
    public static LocalComponentFactoryBase createCachedYahooApi() {
        final LocalComponentFactoryBase factory = new LocalComponentFactoryBase() {
            public LocalComponent getInstance() {
                // Wrap Yahoo API component with the cache
                return new RawDocumentProducerCacheWrapper(
                        new com.dawidweiss.carrot.input.yahoo.YahooApiInputComponent());
            }
        };
        return factory;
    }

    /**
     * <code>filter-stc</code>
     */
    public static LocalComponentFactoryBase createStc() {
        return new LocalComponentFactoryBase() {
            public LocalComponent getInstance() {
                return new STCLocalFilterComponent();
            }
        };
    }
    
    /**
     * <code>filter-rough-kmeans</code>
     */
    public static LocalComponentFactoryBase createRoughKMeans() {
        return new LocalComponentFactoryBase() {
            public LocalComponent getInstance() {
                return new com.chilang.carrot.filter.cluster.local.RoughKMeansLocalFilterComponent();
            }
        };
    }
    
    /**
     * <code>filter-rawdocument-enumerator</code> 
     */
    public static LocalComponentFactoryBase createRawdocumentEnumerator() {
        return new LocalComponentFactoryBase() {
            public LocalComponent getInstance() {
                return new RawDocumentEnumerator();
            }
        };        
    }
    
    /**
     * <code>filter-lingo</code>
     */
    public static LocalComponentFactoryBase createLingo() {
        return new LocalComponentFactoryBase() {
            public LocalComponent getInstance() {
                final HashMap params = new HashMap();
                final Language [] languages = new Language [] {
                    new English()
                };
                return new LingoLocalFilterComponent(languages, params);
            }
        };
    }
}
