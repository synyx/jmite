package org.synyx.jmite.domain.support;

import org.springframework.util.Assert;
import org.springframework.web.util.UriTemplate;


/**
 * Class to extract resource ids from URLs as well as an according pattern. Will
 * be used to derive ids applied by the server to the local entities.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
class IdExtractor<T extends AbstractEntity> {

    private static final String ID_PLACEHOLDER = "{id}";
    private static final String PLACEHOLDER_START =
            ID_PLACEHOLDER.substring(0, 1);


    /**
     * Extracts the id from the given {@code uri} using the given {@code
     * template} and applies it to the given {@code entity}.
     * 
     * @param uri
     * @param template
     * @param entity
     * @return
     */
    public T extract(String uri, String template, T entity) {

        Assert.hasText(uri);
        Assert.hasText(template);
        Assert.notNull(entity);

        Assert.isTrue(null == entity.getId(),
                "Cannot reassign id to an entity that already carries one!");

        Assert.isTrue(template.contains(ID_PLACEHOLDER), String.format(
                "Template must contain id placeholder %s!", ID_PLACEHOLDER));

        String templateBegin =
                template.substring(0, template.indexOf(PLACEHOLDER_START));

        Assert.isTrue(uri.startsWith(templateBegin), String.format(
                "Template %s does not seem to match URI %s!", template, uri));

        UriTemplate uriTemplate = new UriTemplate(template);
        String id = uriTemplate.match(uri).get("id");
        entity.setId(Integer.parseInt(id));

        return entity;
    }
}
