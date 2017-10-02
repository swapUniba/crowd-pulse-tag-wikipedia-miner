package com.github.frapontillo.pulse.crowd.tag.wikipediaminer;

import com.github.frapontillo.pulse.crowd.data.entity.Message;
import com.github.frapontillo.pulse.crowd.data.entity.Tag;
import com.github.frapontillo.pulse.crowd.tag.ITaggerOperator;
import com.github.frapontillo.pulse.spi.IPlugin;
import com.github.frapontillo.pulse.spi.VoidConfig;
import retrofit.RestAdapter;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Francesco Pontillo
 * @see {@link "http://wikipedia-miner.cms.waikato.ac.nz/services/?wikify"}
 */
public class WikipediaMinerTagger extends IPlugin<Message, Message, VoidConfig> {
    public final static String PLUGIN_NAME = "wikipediaminer";
    private final static String WIKIPEDIA_MINER_ENDPOINT =
            "http://wikipedia-miner.cms.waikato.ac.nz";

    private WikipediaMinerService service;

    @Override public String getName() {
        return PLUGIN_NAME;
    }

    @Override public VoidConfig getNewParameter() {
        return new VoidConfig();
    }

    @Override protected Observable.Operator<Message, Message> getOperator(VoidConfig parameters) {
        return new ITaggerOperator(this) {
            @Override protected List<Tag> getTagsImpl(String text, String language) {
                // get the tags
                WikifyResponse response;
                List<Tag> tags = new ArrayList<>();
                try {
                    response = getService().wikify(text, language);
                    for (WikifyResponse.DetectedTopic topic : response.getDetectedTopics()) {
                        Tag tag = new Tag();
                        tag.setText(topic.getTitle());
                        tag.addSource(getName());
                        tags.add(tag);
                    }
                } catch (Exception ignored) {
                }

                // publish the tags as a connectable observable
                return tags;
            }
        };
    }

    private WikipediaMinerService getService() {
        if (service == null) {
            // build the REST client
            RestAdapter restAdapter =
                    new RestAdapter.Builder().setEndpoint(WIKIPEDIA_MINER_ENDPOINT).build();
            service = restAdapter.create(WikipediaMinerService.class);
        }
        return service;
    }
}
