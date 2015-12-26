package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Address;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author gfr
 */
@Stateless
public class AddressController {
  
  @PersistenceContext(name = "address-pu")
  EntityManager em;
  
  public List<Address> findAll(){
    List<Address> res = em.createQuery("Select t from " + Address.class.getSimpleName() + " t").getResultList();
    return res;
  }
  
  public Address get(Long id){
    Address res = em.find(Address.class, id);
    return res;
  }
  
  public Address saveOrUpdate (Address pAdr){
    Address res=em.merge(pAdr);
    return res;
  }
  
  public void delete (Long id){
    Address pAdr = get(id);
    if (pAdr==null){
      throw new WebApplicationException("Entity to be deleted not found for id="+id, 400);
    }
    em.remove(pAdr);
  }
  
}
