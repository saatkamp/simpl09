org.simpl.resource.management.db contains modified SIMPL Core and plug-in
classes, that were integrated to communicate to the PostgreSQL DB directly. 

This was done because the Resource Management used to have its own 
SIMPL Core instance that was accessed via web service, and we want to 
have a more direct access without changing all the written code.

Someone has to find a better solution in the future e.g. using JPA.