package com.mmnaseri.utils.samples.spring.data.jpa.service.impl;

import com.mmnaseri.utils.samples.spring.data.jpa.model.Group;
import com.mmnaseri.utils.samples.spring.data.jpa.model.Membership;
import com.mmnaseri.utils.samples.spring.data.jpa.model.User;
import com.mmnaseri.utils.samples.spring.data.jpa.repository.GroupRepository;
import com.mmnaseri.utils.samples.spring.data.jpa.repository.MembershipRepository;
import com.mmnaseri.utils.samples.spring.data.jpa.service.GroupService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (6/30/16, 9:16 AM)
 */
public class DefaultGroupService implements GroupService {

  private final GroupRepository groupRepository;
  private final MembershipRepository membershipRepository;

  public DefaultGroupService(
      GroupRepository groupRepository, MembershipRepository membershipRepository) {
    this.groupRepository = groupRepository;
    this.membershipRepository = membershipRepository;
  }

  @Override
  public Group createGroup(String name) {
    final Group group = new Group();
    group.setName(name);
    return groupRepository.save(group);
  }

  @Override
  public void deleteGroup(Group group) {
    final List<Membership> memberships = membershipRepository.findByGroup(group);
    membershipRepository.deleteAll(memberships);
    groupRepository.delete(group);
  }

  @Override
  public void join(Group group, User user) {
    if (membershipRepository.findByUserAndGroup(user, group).isPresent()) {
      return;
    }
    final Membership membership = new Membership();
    membership.setGroup(group);
    membership.setUser(user);
    membershipRepository.save(membership);
  }

  @Override
  public void leave(Group group, User user) {
    final Optional<Membership> membership = membershipRepository.findByUserAndGroup(user, group);
    if (!membership.isPresent()) {
      return;
    }
    membershipRepository.delete(membership.get());
  }

  @Override
  public List<User> members(Group group) {
    final List<Membership> memberships = membershipRepository.findByGroup(group);
    final List<User> users = new ArrayList<>();
    for (Membership membership : memberships) {
      users.add(membership.getUser());
    }
    return users;
  }

  @Override
  public List<Group> groups(User user) {
    final List<Membership> memberships = membershipRepository.findByUser(user);
    final List<Group> groups = new ArrayList<>();
    for (Membership membership : memberships) {
      groups.add(membership.getGroup());
    }
    return groups;
  }

  @Override
  public void deactivate(final Group group, final User user) {
    Optional<Membership> optional = membershipRepository.findByUserAndGroup(user, group);
    if (!optional.isPresent()) {
      return;
    }
    Membership membership = optional.get();
    membership.setActive(false);
    membershipRepository.save(membership);
  }

  @Override
  public List<Group> deactivatedGroups(final User user) {
    return membershipRepository.findAllByUserAndActive(user, false).stream()
        .map(Membership::getGroup)
        .collect(toList());
  }
}
