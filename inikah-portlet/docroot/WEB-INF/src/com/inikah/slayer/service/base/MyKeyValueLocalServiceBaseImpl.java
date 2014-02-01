/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.inikah.slayer.service.base;

import com.inikah.slayer.model.MyKeyValue;
import com.inikah.slayer.service.MyKeyValueLocalService;
import com.inikah.slayer.service.persistence.ConfigPersistence;
import com.inikah.slayer.service.persistence.CurrencyPersistence;
import com.inikah.slayer.service.persistence.EarningPersistence;
import com.inikah.slayer.service.persistence.InteractionPersistence;
import com.inikah.slayer.service.persistence.InvitationPersistence;
import com.inikah.slayer.service.persistence.LocationPersistence;
import com.inikah.slayer.service.persistence.MatchCriteriaPersistence;
import com.inikah.slayer.service.persistence.MyKeyValueFinder;
import com.inikah.slayer.service.persistence.MyKeyValuePersistence;
import com.inikah.slayer.service.persistence.MyLanguagePersistence;
import com.inikah.slayer.service.persistence.PaymentPersistence;
import com.inikah.slayer.service.persistence.PhotoPersistence;
import com.inikah.slayer.service.persistence.PlanPersistence;
import com.inikah.slayer.service.persistence.ProfilePersistence;
import com.inikah.slayer.service.persistence.RelativePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the my key value local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.inikah.slayer.service.impl.MyKeyValueLocalServiceImpl}.
 * </p>
 *
 * @author Ahmed Hasan
 * @see com.inikah.slayer.service.impl.MyKeyValueLocalServiceImpl
 * @see com.inikah.slayer.service.MyKeyValueLocalServiceUtil
 * @generated
 */
public abstract class MyKeyValueLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements MyKeyValueLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.inikah.slayer.service.MyKeyValueLocalServiceUtil} to access the my key value local service.
	 */

	/**
	 * Adds the my key value to the database. Also notifies the appropriate model listeners.
	 *
	 * @param myKeyValue the my key value
	 * @return the my key value that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MyKeyValue addMyKeyValue(MyKeyValue myKeyValue)
		throws SystemException {
		myKeyValue.setNew(true);

		return myKeyValuePersistence.update(myKeyValue);
	}

	/**
	 * Creates a new my key value with the primary key. Does not add the my key value to the database.
	 *
	 * @param myKey the primary key for the new my key value
	 * @return the new my key value
	 */
	@Override
	public MyKeyValue createMyKeyValue(long myKey) {
		return myKeyValuePersistence.create(myKey);
	}

	/**
	 * Deletes the my key value with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param myKey the primary key of the my key value
	 * @return the my key value that was removed
	 * @throws PortalException if a my key value with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MyKeyValue deleteMyKeyValue(long myKey)
		throws PortalException, SystemException {
		return myKeyValuePersistence.remove(myKey);
	}

	/**
	 * Deletes the my key value from the database. Also notifies the appropriate model listeners.
	 *
	 * @param myKeyValue the my key value
	 * @return the my key value that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MyKeyValue deleteMyKeyValue(MyKeyValue myKeyValue)
		throws SystemException {
		return myKeyValuePersistence.remove(myKeyValue);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(MyKeyValue.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return myKeyValuePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.inikah.slayer.model.impl.MyKeyValueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return myKeyValuePersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.inikah.slayer.model.impl.MyKeyValueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return myKeyValuePersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return myKeyValuePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return myKeyValuePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public MyKeyValue fetchMyKeyValue(long myKey) throws SystemException {
		return myKeyValuePersistence.fetchByPrimaryKey(myKey);
	}

	/**
	 * Returns the my key value with the primary key.
	 *
	 * @param myKey the primary key of the my key value
	 * @return the my key value
	 * @throws PortalException if a my key value with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MyKeyValue getMyKeyValue(long myKey)
		throws PortalException, SystemException {
		return myKeyValuePersistence.findByPrimaryKey(myKey);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return myKeyValuePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the my key values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.inikah.slayer.model.impl.MyKeyValueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of my key values
	 * @param end the upper bound of the range of my key values (not inclusive)
	 * @return the range of my key values
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MyKeyValue> getMyKeyValues(int start, int end)
		throws SystemException {
		return myKeyValuePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of my key values.
	 *
	 * @return the number of my key values
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getMyKeyValuesCount() throws SystemException {
		return myKeyValuePersistence.countAll();
	}

	/**
	 * Updates the my key value in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param myKeyValue the my key value
	 * @return the my key value that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MyKeyValue updateMyKeyValue(MyKeyValue myKeyValue)
		throws SystemException {
		return myKeyValuePersistence.update(myKeyValue);
	}

	/**
	 * Returns the bridge local service.
	 *
	 * @return the bridge local service
	 */
	public com.inikah.slayer.service.BridgeLocalService getBridgeLocalService() {
		return bridgeLocalService;
	}

	/**
	 * Sets the bridge local service.
	 *
	 * @param bridgeLocalService the bridge local service
	 */
	public void setBridgeLocalService(
		com.inikah.slayer.service.BridgeLocalService bridgeLocalService) {
		this.bridgeLocalService = bridgeLocalService;
	}

	/**
	 * Returns the bridge remote service.
	 *
	 * @return the bridge remote service
	 */
	public com.inikah.slayer.service.BridgeService getBridgeService() {
		return bridgeService;
	}

	/**
	 * Sets the bridge remote service.
	 *
	 * @param bridgeService the bridge remote service
	 */
	public void setBridgeService(
		com.inikah.slayer.service.BridgeService bridgeService) {
		this.bridgeService = bridgeService;
	}

	/**
	 * Returns the config local service.
	 *
	 * @return the config local service
	 */
	public com.inikah.slayer.service.ConfigLocalService getConfigLocalService() {
		return configLocalService;
	}

	/**
	 * Sets the config local service.
	 *
	 * @param configLocalService the config local service
	 */
	public void setConfigLocalService(
		com.inikah.slayer.service.ConfigLocalService configLocalService) {
		this.configLocalService = configLocalService;
	}

	/**
	 * Returns the config remote service.
	 *
	 * @return the config remote service
	 */
	public com.inikah.slayer.service.ConfigService getConfigService() {
		return configService;
	}

	/**
	 * Sets the config remote service.
	 *
	 * @param configService the config remote service
	 */
	public void setConfigService(
		com.inikah.slayer.service.ConfigService configService) {
		this.configService = configService;
	}

	/**
	 * Returns the config persistence.
	 *
	 * @return the config persistence
	 */
	public ConfigPersistence getConfigPersistence() {
		return configPersistence;
	}

	/**
	 * Sets the config persistence.
	 *
	 * @param configPersistence the config persistence
	 */
	public void setConfigPersistence(ConfigPersistence configPersistence) {
		this.configPersistence = configPersistence;
	}

	/**
	 * Returns the currency local service.
	 *
	 * @return the currency local service
	 */
	public com.inikah.slayer.service.CurrencyLocalService getCurrencyLocalService() {
		return currencyLocalService;
	}

	/**
	 * Sets the currency local service.
	 *
	 * @param currencyLocalService the currency local service
	 */
	public void setCurrencyLocalService(
		com.inikah.slayer.service.CurrencyLocalService currencyLocalService) {
		this.currencyLocalService = currencyLocalService;
	}

	/**
	 * Returns the currency persistence.
	 *
	 * @return the currency persistence
	 */
	public CurrencyPersistence getCurrencyPersistence() {
		return currencyPersistence;
	}

	/**
	 * Sets the currency persistence.
	 *
	 * @param currencyPersistence the currency persistence
	 */
	public void setCurrencyPersistence(CurrencyPersistence currencyPersistence) {
		this.currencyPersistence = currencyPersistence;
	}

	/**
	 * Returns the earning local service.
	 *
	 * @return the earning local service
	 */
	public com.inikah.slayer.service.EarningLocalService getEarningLocalService() {
		return earningLocalService;
	}

	/**
	 * Sets the earning local service.
	 *
	 * @param earningLocalService the earning local service
	 */
	public void setEarningLocalService(
		com.inikah.slayer.service.EarningLocalService earningLocalService) {
		this.earningLocalService = earningLocalService;
	}

	/**
	 * Returns the earning remote service.
	 *
	 * @return the earning remote service
	 */
	public com.inikah.slayer.service.EarningService getEarningService() {
		return earningService;
	}

	/**
	 * Sets the earning remote service.
	 *
	 * @param earningService the earning remote service
	 */
	public void setEarningService(
		com.inikah.slayer.service.EarningService earningService) {
		this.earningService = earningService;
	}

	/**
	 * Returns the earning persistence.
	 *
	 * @return the earning persistence
	 */
	public EarningPersistence getEarningPersistence() {
		return earningPersistence;
	}

	/**
	 * Sets the earning persistence.
	 *
	 * @param earningPersistence the earning persistence
	 */
	public void setEarningPersistence(EarningPersistence earningPersistence) {
		this.earningPersistence = earningPersistence;
	}

	/**
	 * Returns the interaction local service.
	 *
	 * @return the interaction local service
	 */
	public com.inikah.slayer.service.InteractionLocalService getInteractionLocalService() {
		return interactionLocalService;
	}

	/**
	 * Sets the interaction local service.
	 *
	 * @param interactionLocalService the interaction local service
	 */
	public void setInteractionLocalService(
		com.inikah.slayer.service.InteractionLocalService interactionLocalService) {
		this.interactionLocalService = interactionLocalService;
	}

	/**
	 * Returns the interaction remote service.
	 *
	 * @return the interaction remote service
	 */
	public com.inikah.slayer.service.InteractionService getInteractionService() {
		return interactionService;
	}

	/**
	 * Sets the interaction remote service.
	 *
	 * @param interactionService the interaction remote service
	 */
	public void setInteractionService(
		com.inikah.slayer.service.InteractionService interactionService) {
		this.interactionService = interactionService;
	}

	/**
	 * Returns the interaction persistence.
	 *
	 * @return the interaction persistence
	 */
	public InteractionPersistence getInteractionPersistence() {
		return interactionPersistence;
	}

	/**
	 * Sets the interaction persistence.
	 *
	 * @param interactionPersistence the interaction persistence
	 */
	public void setInteractionPersistence(
		InteractionPersistence interactionPersistence) {
		this.interactionPersistence = interactionPersistence;
	}

	/**
	 * Returns the invitation local service.
	 *
	 * @return the invitation local service
	 */
	public com.inikah.slayer.service.InvitationLocalService getInvitationLocalService() {
		return invitationLocalService;
	}

	/**
	 * Sets the invitation local service.
	 *
	 * @param invitationLocalService the invitation local service
	 */
	public void setInvitationLocalService(
		com.inikah.slayer.service.InvitationLocalService invitationLocalService) {
		this.invitationLocalService = invitationLocalService;
	}

	/**
	 * Returns the invitation persistence.
	 *
	 * @return the invitation persistence
	 */
	public InvitationPersistence getInvitationPersistence() {
		return invitationPersistence;
	}

	/**
	 * Sets the invitation persistence.
	 *
	 * @param invitationPersistence the invitation persistence
	 */
	public void setInvitationPersistence(
		InvitationPersistence invitationPersistence) {
		this.invitationPersistence = invitationPersistence;
	}

	/**
	 * Returns the location local service.
	 *
	 * @return the location local service
	 */
	public com.inikah.slayer.service.LocationLocalService getLocationLocalService() {
		return locationLocalService;
	}

	/**
	 * Sets the location local service.
	 *
	 * @param locationLocalService the location local service
	 */
	public void setLocationLocalService(
		com.inikah.slayer.service.LocationLocalService locationLocalService) {
		this.locationLocalService = locationLocalService;
	}

	/**
	 * Returns the location remote service.
	 *
	 * @return the location remote service
	 */
	public com.inikah.slayer.service.LocationService getLocationService() {
		return locationService;
	}

	/**
	 * Sets the location remote service.
	 *
	 * @param locationService the location remote service
	 */
	public void setLocationService(
		com.inikah.slayer.service.LocationService locationService) {
		this.locationService = locationService;
	}

	/**
	 * Returns the location persistence.
	 *
	 * @return the location persistence
	 */
	public LocationPersistence getLocationPersistence() {
		return locationPersistence;
	}

	/**
	 * Sets the location persistence.
	 *
	 * @param locationPersistence the location persistence
	 */
	public void setLocationPersistence(LocationPersistence locationPersistence) {
		this.locationPersistence = locationPersistence;
	}

	/**
	 * Returns the match criteria local service.
	 *
	 * @return the match criteria local service
	 */
	public com.inikah.slayer.service.MatchCriteriaLocalService getMatchCriteriaLocalService() {
		return matchCriteriaLocalService;
	}

	/**
	 * Sets the match criteria local service.
	 *
	 * @param matchCriteriaLocalService the match criteria local service
	 */
	public void setMatchCriteriaLocalService(
		com.inikah.slayer.service.MatchCriteriaLocalService matchCriteriaLocalService) {
		this.matchCriteriaLocalService = matchCriteriaLocalService;
	}

	/**
	 * Returns the match criteria persistence.
	 *
	 * @return the match criteria persistence
	 */
	public MatchCriteriaPersistence getMatchCriteriaPersistence() {
		return matchCriteriaPersistence;
	}

	/**
	 * Sets the match criteria persistence.
	 *
	 * @param matchCriteriaPersistence the match criteria persistence
	 */
	public void setMatchCriteriaPersistence(
		MatchCriteriaPersistence matchCriteriaPersistence) {
		this.matchCriteriaPersistence = matchCriteriaPersistence;
	}

	/**
	 * Returns the my key value local service.
	 *
	 * @return the my key value local service
	 */
	public com.inikah.slayer.service.MyKeyValueLocalService getMyKeyValueLocalService() {
		return myKeyValueLocalService;
	}

	/**
	 * Sets the my key value local service.
	 *
	 * @param myKeyValueLocalService the my key value local service
	 */
	public void setMyKeyValueLocalService(
		com.inikah.slayer.service.MyKeyValueLocalService myKeyValueLocalService) {
		this.myKeyValueLocalService = myKeyValueLocalService;
	}

	/**
	 * Returns the my key value persistence.
	 *
	 * @return the my key value persistence
	 */
	public MyKeyValuePersistence getMyKeyValuePersistence() {
		return myKeyValuePersistence;
	}

	/**
	 * Sets the my key value persistence.
	 *
	 * @param myKeyValuePersistence the my key value persistence
	 */
	public void setMyKeyValuePersistence(
		MyKeyValuePersistence myKeyValuePersistence) {
		this.myKeyValuePersistence = myKeyValuePersistence;
	}

	/**
	 * Returns the my key value finder.
	 *
	 * @return the my key value finder
	 */
	public MyKeyValueFinder getMyKeyValueFinder() {
		return myKeyValueFinder;
	}

	/**
	 * Sets the my key value finder.
	 *
	 * @param myKeyValueFinder the my key value finder
	 */
	public void setMyKeyValueFinder(MyKeyValueFinder myKeyValueFinder) {
		this.myKeyValueFinder = myKeyValueFinder;
	}

	/**
	 * Returns the my language local service.
	 *
	 * @return the my language local service
	 */
	public com.inikah.slayer.service.MyLanguageLocalService getMyLanguageLocalService() {
		return myLanguageLocalService;
	}

	/**
	 * Sets the my language local service.
	 *
	 * @param myLanguageLocalService the my language local service
	 */
	public void setMyLanguageLocalService(
		com.inikah.slayer.service.MyLanguageLocalService myLanguageLocalService) {
		this.myLanguageLocalService = myLanguageLocalService;
	}

	/**
	 * Returns the my language persistence.
	 *
	 * @return the my language persistence
	 */
	public MyLanguagePersistence getMyLanguagePersistence() {
		return myLanguagePersistence;
	}

	/**
	 * Sets the my language persistence.
	 *
	 * @param myLanguagePersistence the my language persistence
	 */
	public void setMyLanguagePersistence(
		MyLanguagePersistence myLanguagePersistence) {
		this.myLanguagePersistence = myLanguagePersistence;
	}

	/**
	 * Returns the payment local service.
	 *
	 * @return the payment local service
	 */
	public com.inikah.slayer.service.PaymentLocalService getPaymentLocalService() {
		return paymentLocalService;
	}

	/**
	 * Sets the payment local service.
	 *
	 * @param paymentLocalService the payment local service
	 */
	public void setPaymentLocalService(
		com.inikah.slayer.service.PaymentLocalService paymentLocalService) {
		this.paymentLocalService = paymentLocalService;
	}

	/**
	 * Returns the payment persistence.
	 *
	 * @return the payment persistence
	 */
	public PaymentPersistence getPaymentPersistence() {
		return paymentPersistence;
	}

	/**
	 * Sets the payment persistence.
	 *
	 * @param paymentPersistence the payment persistence
	 */
	public void setPaymentPersistence(PaymentPersistence paymentPersistence) {
		this.paymentPersistence = paymentPersistence;
	}

	/**
	 * Returns the photo local service.
	 *
	 * @return the photo local service
	 */
	public com.inikah.slayer.service.PhotoLocalService getPhotoLocalService() {
		return photoLocalService;
	}

	/**
	 * Sets the photo local service.
	 *
	 * @param photoLocalService the photo local service
	 */
	public void setPhotoLocalService(
		com.inikah.slayer.service.PhotoLocalService photoLocalService) {
		this.photoLocalService = photoLocalService;
	}

	/**
	 * Returns the photo persistence.
	 *
	 * @return the photo persistence
	 */
	public PhotoPersistence getPhotoPersistence() {
		return photoPersistence;
	}

	/**
	 * Sets the photo persistence.
	 *
	 * @param photoPersistence the photo persistence
	 */
	public void setPhotoPersistence(PhotoPersistence photoPersistence) {
		this.photoPersistence = photoPersistence;
	}

	/**
	 * Returns the plan local service.
	 *
	 * @return the plan local service
	 */
	public com.inikah.slayer.service.PlanLocalService getPlanLocalService() {
		return planLocalService;
	}

	/**
	 * Sets the plan local service.
	 *
	 * @param planLocalService the plan local service
	 */
	public void setPlanLocalService(
		com.inikah.slayer.service.PlanLocalService planLocalService) {
		this.planLocalService = planLocalService;
	}

	/**
	 * Returns the plan persistence.
	 *
	 * @return the plan persistence
	 */
	public PlanPersistence getPlanPersistence() {
		return planPersistence;
	}

	/**
	 * Sets the plan persistence.
	 *
	 * @param planPersistence the plan persistence
	 */
	public void setPlanPersistence(PlanPersistence planPersistence) {
		this.planPersistence = planPersistence;
	}

	/**
	 * Returns the profile local service.
	 *
	 * @return the profile local service
	 */
	public com.inikah.slayer.service.ProfileLocalService getProfileLocalService() {
		return profileLocalService;
	}

	/**
	 * Sets the profile local service.
	 *
	 * @param profileLocalService the profile local service
	 */
	public void setProfileLocalService(
		com.inikah.slayer.service.ProfileLocalService profileLocalService) {
		this.profileLocalService = profileLocalService;
	}

	/**
	 * Returns the profile remote service.
	 *
	 * @return the profile remote service
	 */
	public com.inikah.slayer.service.ProfileService getProfileService() {
		return profileService;
	}

	/**
	 * Sets the profile remote service.
	 *
	 * @param profileService the profile remote service
	 */
	public void setProfileService(
		com.inikah.slayer.service.ProfileService profileService) {
		this.profileService = profileService;
	}

	/**
	 * Returns the profile persistence.
	 *
	 * @return the profile persistence
	 */
	public ProfilePersistence getProfilePersistence() {
		return profilePersistence;
	}

	/**
	 * Sets the profile persistence.
	 *
	 * @param profilePersistence the profile persistence
	 */
	public void setProfilePersistence(ProfilePersistence profilePersistence) {
		this.profilePersistence = profilePersistence;
	}

	/**
	 * Returns the relative local service.
	 *
	 * @return the relative local service
	 */
	public com.inikah.slayer.service.RelativeLocalService getRelativeLocalService() {
		return relativeLocalService;
	}

	/**
	 * Sets the relative local service.
	 *
	 * @param relativeLocalService the relative local service
	 */
	public void setRelativeLocalService(
		com.inikah.slayer.service.RelativeLocalService relativeLocalService) {
		this.relativeLocalService = relativeLocalService;
	}

	/**
	 * Returns the relative remote service.
	 *
	 * @return the relative remote service
	 */
	public com.inikah.slayer.service.RelativeService getRelativeService() {
		return relativeService;
	}

	/**
	 * Sets the relative remote service.
	 *
	 * @param relativeService the relative remote service
	 */
	public void setRelativeService(
		com.inikah.slayer.service.RelativeService relativeService) {
		this.relativeService = relativeService;
	}

	/**
	 * Returns the relative persistence.
	 *
	 * @return the relative persistence
	 */
	public RelativePersistence getRelativePersistence() {
		return relativePersistence;
	}

	/**
	 * Sets the relative persistence.
	 *
	 * @param relativePersistence the relative persistence
	 */
	public void setRelativePersistence(RelativePersistence relativePersistence) {
		this.relativePersistence = relativePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.inikah.slayer.model.MyKeyValue",
			myKeyValueLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.inikah.slayer.model.MyKeyValue");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return MyKeyValue.class;
	}

	protected String getModelClassName() {
		return MyKeyValue.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = myKeyValuePersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.inikah.slayer.service.BridgeLocalService.class)
	protected com.inikah.slayer.service.BridgeLocalService bridgeLocalService;
	@BeanReference(type = com.inikah.slayer.service.BridgeService.class)
	protected com.inikah.slayer.service.BridgeService bridgeService;
	@BeanReference(type = com.inikah.slayer.service.ConfigLocalService.class)
	protected com.inikah.slayer.service.ConfigLocalService configLocalService;
	@BeanReference(type = com.inikah.slayer.service.ConfigService.class)
	protected com.inikah.slayer.service.ConfigService configService;
	@BeanReference(type = ConfigPersistence.class)
	protected ConfigPersistence configPersistence;
	@BeanReference(type = com.inikah.slayer.service.CurrencyLocalService.class)
	protected com.inikah.slayer.service.CurrencyLocalService currencyLocalService;
	@BeanReference(type = CurrencyPersistence.class)
	protected CurrencyPersistence currencyPersistence;
	@BeanReference(type = com.inikah.slayer.service.EarningLocalService.class)
	protected com.inikah.slayer.service.EarningLocalService earningLocalService;
	@BeanReference(type = com.inikah.slayer.service.EarningService.class)
	protected com.inikah.slayer.service.EarningService earningService;
	@BeanReference(type = EarningPersistence.class)
	protected EarningPersistence earningPersistence;
	@BeanReference(type = com.inikah.slayer.service.InteractionLocalService.class)
	protected com.inikah.slayer.service.InteractionLocalService interactionLocalService;
	@BeanReference(type = com.inikah.slayer.service.InteractionService.class)
	protected com.inikah.slayer.service.InteractionService interactionService;
	@BeanReference(type = InteractionPersistence.class)
	protected InteractionPersistence interactionPersistence;
	@BeanReference(type = com.inikah.slayer.service.InvitationLocalService.class)
	protected com.inikah.slayer.service.InvitationLocalService invitationLocalService;
	@BeanReference(type = InvitationPersistence.class)
	protected InvitationPersistence invitationPersistence;
	@BeanReference(type = com.inikah.slayer.service.LocationLocalService.class)
	protected com.inikah.slayer.service.LocationLocalService locationLocalService;
	@BeanReference(type = com.inikah.slayer.service.LocationService.class)
	protected com.inikah.slayer.service.LocationService locationService;
	@BeanReference(type = LocationPersistence.class)
	protected LocationPersistence locationPersistence;
	@BeanReference(type = com.inikah.slayer.service.MatchCriteriaLocalService.class)
	protected com.inikah.slayer.service.MatchCriteriaLocalService matchCriteriaLocalService;
	@BeanReference(type = MatchCriteriaPersistence.class)
	protected MatchCriteriaPersistence matchCriteriaPersistence;
	@BeanReference(type = com.inikah.slayer.service.MyKeyValueLocalService.class)
	protected com.inikah.slayer.service.MyKeyValueLocalService myKeyValueLocalService;
	@BeanReference(type = MyKeyValuePersistence.class)
	protected MyKeyValuePersistence myKeyValuePersistence;
	@BeanReference(type = MyKeyValueFinder.class)
	protected MyKeyValueFinder myKeyValueFinder;
	@BeanReference(type = com.inikah.slayer.service.MyLanguageLocalService.class)
	protected com.inikah.slayer.service.MyLanguageLocalService myLanguageLocalService;
	@BeanReference(type = MyLanguagePersistence.class)
	protected MyLanguagePersistence myLanguagePersistence;
	@BeanReference(type = com.inikah.slayer.service.PaymentLocalService.class)
	protected com.inikah.slayer.service.PaymentLocalService paymentLocalService;
	@BeanReference(type = PaymentPersistence.class)
	protected PaymentPersistence paymentPersistence;
	@BeanReference(type = com.inikah.slayer.service.PhotoLocalService.class)
	protected com.inikah.slayer.service.PhotoLocalService photoLocalService;
	@BeanReference(type = PhotoPersistence.class)
	protected PhotoPersistence photoPersistence;
	@BeanReference(type = com.inikah.slayer.service.PlanLocalService.class)
	protected com.inikah.slayer.service.PlanLocalService planLocalService;
	@BeanReference(type = PlanPersistence.class)
	protected PlanPersistence planPersistence;
	@BeanReference(type = com.inikah.slayer.service.ProfileLocalService.class)
	protected com.inikah.slayer.service.ProfileLocalService profileLocalService;
	@BeanReference(type = com.inikah.slayer.service.ProfileService.class)
	protected com.inikah.slayer.service.ProfileService profileService;
	@BeanReference(type = ProfilePersistence.class)
	protected ProfilePersistence profilePersistence;
	@BeanReference(type = com.inikah.slayer.service.RelativeLocalService.class)
	protected com.inikah.slayer.service.RelativeLocalService relativeLocalService;
	@BeanReference(type = com.inikah.slayer.service.RelativeService.class)
	protected com.inikah.slayer.service.RelativeService relativeService;
	@BeanReference(type = RelativePersistence.class)
	protected RelativePersistence relativePersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private MyKeyValueLocalServiceClpInvoker _clpInvoker = new MyKeyValueLocalServiceClpInvoker();
}